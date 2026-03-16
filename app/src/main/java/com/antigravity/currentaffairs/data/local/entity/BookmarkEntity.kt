package com.antigravity.currentaffairs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val affairId: String,
    val titleEnglish: String,
    val titleHindi: String,
    val keyPointsEnglish: String,
    val keyPointsHindi: String,
    val imageUrl: String?,
    val category: String,
    val sourceUrl: String,
    val sourceName: String,
    val relevanceScore: Int,
    val date: String,
    val timestamp: Long,
    val bookmarkedAt: Long = System.currentTimeMillis()
)
