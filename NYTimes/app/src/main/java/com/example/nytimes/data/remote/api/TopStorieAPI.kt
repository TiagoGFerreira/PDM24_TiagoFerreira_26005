package com.example.nytimes.data.remote.api

import com.example.nytimes.data.remote.model.single_news.SingleNewsItemDTO
import com.example.nytimes.domain.model.top_news.TopNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TopStoriesApi {
    @GET("top-news")
    suspend fun getTopStories(
        @Query("api-key") apiKey: String,
        @Query("source-country") sourceCountry: String,
        @Query("language") language: String
    ): TopNewsResponse

    @GET("retrieve-news")
    suspend fun getTopStorieDetail(
        @Query("ids") topStorieId: Int,
        @Query("api-key") apiKey: String,
    ): SingleNewsItemDTO
}
