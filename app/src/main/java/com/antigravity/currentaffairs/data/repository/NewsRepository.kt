package com.antigravity.currentaffairs.data.repository

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.RawNewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchRawNewsFromAllSources(): List<RawNewsItem>
    suspend fun fetchAndStoreDailyAffairs(): List<CurrentAffair>
    fun getAffairsByDate(date: String): Flow<List<CurrentAffair>>
    fun getAffairsByDateAndCategory(date: String, category: String): Flow<List<CurrentAffair>>
    suspend fun getAffairById(id: String): CurrentAffair?
    suspend fun updateBookmarkStatus(id: String, isBookmarked: Boolean)
    suspend fun cleanupOldData(keepDays: Int = 30)
    suspend fun getAffairsCountByDate(date: String): Int
}
