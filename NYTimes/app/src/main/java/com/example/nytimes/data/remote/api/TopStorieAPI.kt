package com.example.nytimes.data.remote.api

import com.example.nytimes.data.remote.model.TopStorieDTO
import com.example.nytimes.data.remote.model.TopStorieDetailDTO
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
    @GET("top-news")
    suspend fun getTopStories(
        @Query("api-key") apiKey: String,
        @Path("source-country") srccountry: String,
        @Path("language") language: String
    ): List<TopStorieDTO>

    @GET("top-news/{id}")
    suspend fun getTopStorieDetail(
        @Query("api-key") apiKey: String,
        @Path("source-country") srccountry: String,
        @Path("language") language: String
    ): List<TopStorieDetailDTO>
}
