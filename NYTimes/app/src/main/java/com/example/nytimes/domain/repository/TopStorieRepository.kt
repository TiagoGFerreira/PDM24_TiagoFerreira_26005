package com.example.nytimes.domain.repository


import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.model.NewsDetail

interface TopStorieRepository {
    suspend fun getTopStories(apikey: String, source : String, language : String): List<NewsItem>
    suspend fun getTopStoriesDetail(topStorieId: Int, apikey: String) : NewsDetail
}