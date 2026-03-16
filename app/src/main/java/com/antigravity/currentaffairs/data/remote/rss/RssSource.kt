package com.antigravity.currentaffairs.data.remote.rss

data class RssSource(
    val name: String,
    val url: String,
    val language: String,
    val isGovernment: Boolean = false,
    val priority: Int = 0
)
