package com.example.nytimes.domain.repository


import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.model.SingleNews

interface TopStorieRepository {
    suspend fun getTopStories(): List<NewsItem>
    suspend fun getTopStoriesDetail(topStorieId: Int, apikey: String) : SingleNews
}