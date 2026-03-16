package com.antigravity.currentaffairs.data.model

data class CurrentAffair(
    val id: String,
    val titleEnglish: String,
    val titleHindi: String,
    val keyPointsEnglish: List<String>,
    val keyPointsHindi: List<String>,
    val imageUrl: String?,
    val category: Category,
    val sourceUrl: String,
    val sourceName: String,
    val relevanceScore: Int,
    val date: String,
    val isBookmarked: Boolean = false,
    val timestamp: Long
)
