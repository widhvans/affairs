package com.antigravity.currentaffairs.domain.engine

import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.RawNewsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentFilter @Inject constructor(
    private val relevanceScorer: RelevanceScorer
) {

    private val irrelevantKeywords = listOf(
        "entertainment", "bollywood", "celebrity", "gossip",
        "horoscope", "astrology", "lifestyle", "fashion",
        "recipe", "cooking", "beauty", "skincare",
        "dating", "relationship", "viral video", "meme",
        "tiktok", "instagram", "influencer"
    )

    private val minimumScoreThreshold = 15

    fun filterRelevantNews(items: List<RawNewsItem>): List<RawNewsItem> {
        return items
            .filter { item -> !isIrrelevant(item) }
            .filter { item -> hasMinimumQuality(item) }
            .filter { item -> relevanceScorer.calculateScore(item) >= minimumScoreThreshold }
            .sortedByDescending { item -> relevanceScorer.calculateScore(item) }
    }

    fun detectCategory(item: RawNewsItem): Category {
        val combined = "${item.title} ${item.description}".lowercase()
        val categoryKeywords = relevanceScorer.getCategoryKeywords()

        var bestCategory = Category.NATIONAL
        var bestScore = 0

        categoryKeywords.forEach { (category, keywords) ->
            val score = keywords.count { keyword -> keyword in combined }
            if (score > bestScore) {
                bestScore = score
                bestCategory = category
            }
        }

        // Also check raw category if available
        item.rawCategory?.let { rawCat ->
            val rawLower = rawCat.lowercase()
            when {
                rawLower.contains("sport") -> return Category.SPORTS
                rawLower.contains("business") || rawLower.contains("econom") -> return Category.ECONOMY
                rawLower.contains("tech") || rawLower.contains("science") -> return Category.SCIENCE_TECH
                rawLower.contains("world") || rawLower.contains("international") -> return Category.INTERNATIONAL
                rawLower.contains("defense") || rawLower.contains("military") -> return Category.DEFENSE
                rawLower.contains("environment") || rawLower.contains("climate") -> return Category.ENVIRONMENT
            }
        }

        return bestCategory
    }

    private fun isIrrelevant(item: RawNewsItem): Boolean {
        val combined = "${item.title} ${item.description}".lowercase()
        return irrelevantKeywords.any { it in combined }
    }

    private fun hasMinimumQuality(item: RawNewsItem): Boolean {
        // Title should be meaningful
        if (item.title.length < 15) return false
        if (item.title.split(" ").size < 3) return false

        // Not all caps
        if (item.title == item.title.uppercase() && item.title.length > 30) return false

        return true
    }
}
