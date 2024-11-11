package com.example.nytimes.data.remote.api

import com.example.nytimes.data.remote.model.TopStorieDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    val api: TopStoriesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopStoriesApi::class.java)
    }
}

interface TopStoriesApi {
    @GET("svc/topstories/v2/home.json")
    suspend fun getTopStories(
        @Query("api-key") apiKey: String
    ): List<TopStoriesDto>
}
