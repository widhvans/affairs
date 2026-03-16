package com.antigravity.currentaffairs.data.repository

import com.antigravity.currentaffairs.data.local.dao.BookmarkDao
import com.antigravity.currentaffairs.data.local.entity.BookmarkEntity
import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val bookmarkDao: BookmarkDao,
    private val gson: Gson
) {

    fun getAllBookmarks(): Flow<List<CurrentAffair>> {
        return bookmarkDao.getAllBookmarks().map { entities ->
            entities.map { it.toCurrentAffair(gson) }
        }
    }

    fun searchBookmarks(query: String): Flow<List<CurrentAffair>> {
        return bookmarkDao.searchBookmarks(query).map { entities ->
            entities.map { it.toCurrentAffair(gson) }
        }
    }

    fun getAllBookmarkDates(): Flow<List<String>> {
        return bookmarkDao.getAllBookmarkDates()
    }

    suspend fun addBookmark(affair: CurrentAffair) {
        val entity = BookmarkEntity(
            affairId = affair.id,
            titleEnglish = affair.titleEnglish,
            titleHindi = affair.titleHindi,
            keyPointsEnglish = gson.toJson(affair.keyPointsEnglish),
            keyPointsHindi = gson.toJson(affair.keyPointsHindi),
            imageUrl = affair.imageUrl,
            category = affair.category.name,
            sourceUrl = affair.sourceUrl,
            sourceName = affair.sourceName,
            relevanceScore = affair.relevanceScore,
            date = affair.date,
            timestamp = affair.timestamp
        )
        bookmarkDao.insert(entity)
    }

    suspend fun removeBookmark(affairId: String) {
        bookmarkDao.deleteById(affairId)
    }

    suspend fun isBookmarked(affairId: String): Boolean {
        return bookmarkDao.getBookmarkById(affairId) != null
    }

    suspend fun getBookmarkCount(): Int {
        return bookmarkDao.getBookmarkCount()
    }
}

private fun BookmarkEntity.toCurrentAffair(gson: Gson): CurrentAffair {
    val listType = object : TypeToken<List<String>>() {}.type
    return CurrentAffair(
        id = affairId,
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
        isBookmarked = true,
        timestamp = timestamp
    )
}
