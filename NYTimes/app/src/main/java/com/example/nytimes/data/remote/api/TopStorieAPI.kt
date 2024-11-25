package com.example.nytimes.data.remote.api

import com.example.nytimes.data.remote.model.SingleNewsItemDTO
import com.example.nytimes.data.remote.model.TopStorieDetailDTO
import com.example.nytimes.domain.model.SingleNews
import com.example.nytimes.domain.model.TopNewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitInstance {
    val api: TopStoriesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.worldnewsapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopStoriesApi::class.java)
    }
}

interface TopStoriesApi {
    @GET("top-news/")
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
