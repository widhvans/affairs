package com.antigravity.currentaffairs.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

data class CurrentsResponse(
    val status: String = "",
    val news: List<CurrentsArticle> = emptyList()
)

data class CurrentsArticle(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val image: String? = null,
    val published: String = "",
    val category: List<String> = emptyList(),
    val language: String = ""
)

interface CurrentsApiService {

    @GET("latest-news")
    suspend fun getLatestNews(
        @Query("language") language: String = "en",
        @Query("country") country: String = "IN",
        @Query("apiKey") apiKey: String = ApiConstants.CURRENTS_API_KEY
    ): CurrentsResponse

    @GET("search")
    suspend fun searchNews(
        @Query("keywords") keywords: String,
        @Query("language") language: String = "en",
        @Query("country") country: String = "IN",
        @Query("apiKey") apiKey: String = ApiConstants.CURRENTS_API_KEY
    ): CurrentsResponse
}
