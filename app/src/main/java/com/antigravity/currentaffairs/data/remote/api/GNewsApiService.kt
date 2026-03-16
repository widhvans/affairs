package com.antigravity.currentaffairs.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

data class GNewsResponse(
    val totalArticles: Int = 0,
    val articles: List<GNewsArticle> = emptyList()
)

data class GNewsArticle(
    val title: String = "",
    val description: String = "",
    val content: String = "",
    val url: String = "",
    val image: String? = null,
    val publishedAt: String = "",
    val source: GNewsSource = GNewsSource()
)

data class GNewsSource(
    val name: String = "",
    val url: String = ""
)

interface GNewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("category") category: String = "general",
        @Query("lang") lang: String = "en",
        @Query("max") max: Int = 50,
        @Query("apikey") apiKey: String = ApiConstants.GNEWS_API_KEY
    ): GNewsResponse

    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("country") country: String = "in",
        @Query("lang") lang: String = "en",
        @Query("max") max: Int = 50,
        @Query("apikey") apiKey: String = ApiConstants.GNEWS_API_KEY
    ): GNewsResponse
}
