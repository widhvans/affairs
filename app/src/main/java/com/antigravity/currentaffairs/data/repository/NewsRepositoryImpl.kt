package com.antigravity.currentaffairs.data.repository

import com.antigravity.currentaffairs.data.local.dao.CurrentAffairDao
import com.antigravity.currentaffairs.data.local.entity.CurrentAffairEntity
import com.antigravity.currentaffairs.data.local.PreferenceManager
import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.RawNewsItem
import com.antigravity.currentaffairs.data.remote.api.ApiConstants
import com.antigravity.currentaffairs.data.remote.api.GNewsApiService
import com.antigravity.currentaffairs.data.remote.api.CurrentsApiService
import com.antigravity.currentaffairs.data.remote.rss.RssFeedConfig
import com.antigravity.currentaffairs.data.remote.rss.RssFeedParser
import com.antigravity.currentaffairs.domain.engine.ContentEnricher
import com.antigravity.currentaffairs.domain.engine.ContentFilter
import com.antigravity.currentaffairs.domain.engine.RelevanceScorer
import com.antigravity.currentaffairs.domain.engine.SimilarityChecker
import com.antigravity.currentaffairs.domain.usecase.TranslateUseCase
import com.antigravity.currentaffairs.utils.DateUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val currentAffairDao: CurrentAffairDao,
    private val rssFeedParser: RssFeedParser,
    private val gNewsApiService: GNewsApiService,
    private val currentsApiService: CurrentsApiService,
    private val relevanceScorer: RelevanceScorer,
    private val contentFilter: ContentFilter,
    private val similarityChecker: SimilarityChecker,
    private val contentEnricher: ContentEnricher,
    private val translateUseCase: TranslateUseCase,
    private val preferenceManager: PreferenceManager,
    private val gson: Gson
) : NewsRepository {

    override suspend fun fetchRawNewsFromAllSources(): List<RawNewsItem> {
        return withContext(Dispatchers.IO) {
            val allItems = mutableListOf<RawNewsItem>()

            // 1. Fetch from RSS feeds
            try {
                val rssItems = rssFeedParser.parseMultipleFeeds(RssFeedConfig.allFeeds)
                allItems.addAll(rssItems)
            } catch (e: Exception) {
                // Continue with other sources
            }

            // 2. Fetch from GNews API (if key available)
            if (ApiConstants.GNEWS_API_KEY.isNotBlank()) {
                try {
                    val gNewsResponse = gNewsApiService.getTopHeadlines()
                    val gNewsItems = gNewsResponse.articles.map { article ->
                        RawNewsItem(
                            title = article.title,
                            description = article.description,
                            sourceUrl = article.url,
                            sourceName = article.source.name.ifBlank { "GNews" },
                            imageUrl = article.image,
                            publishedDate = DateUtils.parseIsoDate(article.publishedAt),
                            rawCategory = null
                        )
                    }
                    allItems.addAll(gNewsItems)
                } catch (e: Exception) {
                    // Continue
                }
            }

            // 3. Fetch from Currents API (if key available)
            if (ApiConstants.CURRENTS_API_KEY.isNotBlank()) {
                try {
                    val currentsResponse = currentsApiService.getLatestNews()
                    val currentsItems = currentsResponse.news.map { article ->
                        RawNewsItem(
                            title = article.title,
                            description = article.description,
                            sourceUrl = article.url,
                            sourceName = "Currents API",
                            imageUrl = article.image,
                            publishedDate = DateUtils.parseIsoDate(article.published),
                            rawCategory = article.category.firstOrNull()
                        )
                    }
                    allItems.addAll(currentsItems)
                } catch (e: Exception) {
                    // Continue
                }
            }

            allItems
        }
    }

    override suspend fun fetchAndStoreDailyAffairs(): List<CurrentAffair> {
        return withContext(Dispatchers.IO) {
            val previousDay = DateUtils.getPreviousDay()
            val dateStr = previousDay.format(DateTimeFormatter.ISO_LOCAL_DATE)

            // Check if we already have data for this date
            val existingCount = currentAffairDao.getAffairsCountByDate(dateStr)
            if (existingCount >= 20) {
                // Already fetched, return cached data
                return@withContext getCachedAffairs(dateStr)
            }

            // Step 1: Aggregate raw news
            val rawItems = fetchRawNewsFromAllSources()

            if (rawItems.isEmpty()) {
                return@withContext getCachedAffairs(dateStr)
            }

            // Step 2: Filter by exam relevance
            val filteredItems = contentFilter.filterRelevantNews(rawItems)

            // Step 3: Score and rank
            val scoredItems = filteredItems.map { item ->
                val score = relevanceScorer.calculateScore(item)
                Pair(item, score)
            }.sortedByDescending { it.second }

            // Step 4: Deduplicate
            val existingTitles = currentAffairDao.getAllTitles()
            val deduplicated = similarityChecker.deduplicateRawItems(
                scoredItems.map { it.first },
                existingTitles
            )

            // Step 5: Enrich and select top 20
            val enrichedItems = selectTop20WithDiversity(deduplicated, dateStr)

            // Step 6: Translate and store
            val translatedItems = enrichedItems.map { affair ->
                translateAndEnrich(affair)
            }

            // Store in database
            val entities = translatedItems.map { it.toEntity(gson) }
            currentAffairDao.insertAll(entities)

            // Update last fetch date
            preferenceManager.setLastFetchDate(dateStr)

            translatedItems
        }
    }

    private suspend fun selectTop20WithDiversity(
        items: List<RawNewsItem>,
        dateStr: String
    ): List<CurrentAffair> {
        val categoryCount = mutableMapOf<Category, Int>()
        val selected = mutableListOf<CurrentAffair>()

        for (item in items) {
            if (selected.size >= 20) break

            val category = contentFilter.detectCategory(item)
            val currentCount = categoryCount.getOrDefault(category, 0)

            // Max 4 per category for diversity
            if (currentCount >= 4) continue

            val keyPoints = contentEnricher.extractKeyPoints(item.description)
            val affair = CurrentAffair(
                id = UUID.randomUUID().toString(),
                titleEnglish = contentEnricher.cleanTitle(item.title),
                titleHindi = "", // Will be translated
                keyPointsEnglish = keyPoints,
                keyPointsHindi = emptyList(), // Will be translated
                imageUrl = item.imageUrl,
                category = category,
                sourceUrl = item.sourceUrl,
                sourceName = item.sourceName,
                relevanceScore = relevanceScorer.calculateScore(item),
                date = dateStr,
                isBookmarked = false,
                timestamp = System.currentTimeMillis()
            )

            selected.add(affair)
            categoryCount[category] = currentCount + 1
        }

        return selected
    }

    private suspend fun translateAndEnrich(affair: CurrentAffair): CurrentAffair {
        val titleHindi = try {
            translateUseCase.translateToHindi(affair.titleEnglish)
        } catch (e: Exception) {
            affair.titleEnglish
        }

        val keyPointsHindi = try {
            affair.keyPointsEnglish.map { point ->
                translateUseCase.translateToHindi(point)
            }
        } catch (e: Exception) {
            affair.keyPointsEnglish
        }

        return affair.copy(
            titleHindi = titleHindi,
            keyPointsHindi = keyPointsHindi
        )
    }

    private suspend fun getCachedAffairs(dateStr: String): List<CurrentAffair> {
        val entities = mutableListOf<CurrentAffairEntity>()
        currentAffairDao.getAffairsByDate(dateStr).collect { list ->
            entities.addAll(list)
            return@collect
        }
        return entities.map { it.toCurrentAffair(gson) }
    }

    override fun getAffairsByDate(date: String): Flow<List<CurrentAffair>> {
        return currentAffairDao.getAffairsByDate(date).map { entities ->
            entities.map { it.toCurrentAffair(gson) }
        }
    }

    override fun getAffairsByDateAndCategory(date: String, category: String): Flow<List<CurrentAffair>> {
        return currentAffairDao.getAffairsByDateAndCategory(date, category).map { entities ->
            entities.map { it.toCurrentAffair(gson) }
        }
    }

    override suspend fun getAffairById(id: String): CurrentAffair? {
        return currentAffairDao.getAffairById(id)?.toCurrentAffair(gson)
    }

    override suspend fun updateBookmarkStatus(id: String, isBookmarked: Boolean) {
        currentAffairDao.updateBookmarkStatus(id, isBookmarked)
    }

    override suspend fun cleanupOldData(keepDays: Int) {
        val cutoffDate = LocalDate.now().minusDays(keepDays.toLong())
            .format(DateTimeFormatter.ISO_LOCAL_DATE)
        currentAffairDao.deleteOlderThan(cutoffDate)
    }

    override suspend fun getAffairsCountByDate(date: String): Int {
        return currentAffairDao.getAffairsCountByDate(date)
    }
}

// Extension functions for entity conversion
fun CurrentAffair.toEntity(gson: Gson): CurrentAffairEntity {
    return CurrentAffairEntity(
        id = id,
        titleEnglish = titleEnglish,
        titleHindi = titleHindi,
        keyPointsEnglish = gson.toJson(keyPointsEnglish),
        keyPointsHindi = gson.toJson(keyPointsHindi),
        imageUrl = imageUrl,
        category = category.name,
        sourceUrl = sourceUrl,
        sourceName = sourceName,
        relevanceScore = relevanceScore,
        date = date,
        isBookmarked = isBookmarked,
        timestamp = timestamp
    )
}

fun CurrentAffairEntity.toCurrentAffair(gson: Gson): CurrentAffair {
    val listType = object : TypeToken<List<String>>() {}.type
    return CurrentAffair(
        id = id,
        titleEnglish = titleEnglish,
        titleHindi = titleHindi,
        keyPointsEnglish = try {
            gson.fromJson(keyPointsEnglish, listType)
        } catch (e: Exception) {
            listOf(keyPointsEnglish)
        },
        keyPointsHindi = try {
            gson.fromJson(keyPointsHindi, listType)
        } catch (e: Exception) {
            listOf(keyPointsHindi)
        },
        imageUrl = imageUrl,
        category = try {
            Category.valueOf(category)
        } catch (e: Exception) {
            Category.NATIONAL
        },
        sourceUrl = sourceUrl,
        sourceName = sourceName,
        relevanceScore = relevanceScore,
        date = date,
        isBookmarked = isBookmarked,
        timestamp = timestamp
    )
}
