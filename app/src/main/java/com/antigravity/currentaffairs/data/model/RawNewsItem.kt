package com.antigravity.currentaffairs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RawNewsItem(
    val title: String,
    val titleHindi: String? = null,
    val description: String,
    val descriptionHindi: String? = null,
    val sourceUrl: String,
    val sourceName: String,
    val imageUrl: String? = null,
    val publishedDate: Long,
    val rawCategory: String? = null
)
