package com.example.nytimes.domain.repository


import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.model.TopStorieDetail

interface TopStorieRepository {
    suspend fun getTopStories(): List<NewsItem>
    suspend fun getTopStoriesDetail(topStorieId: String, language: String, srccountry: String) : TopStorieDetail
}