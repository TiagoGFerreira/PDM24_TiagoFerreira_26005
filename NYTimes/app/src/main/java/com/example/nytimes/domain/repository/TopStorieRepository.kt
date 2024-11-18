package com.example.nytimes.domain.repository

import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.model.TopStorieDetail

interface TopStorieRepository {
    suspend fun getTopStories(): List<TopStorie>
    suspend fun getTopStoriesDetail(topStorieId: String, language: String, srccountry: String) : TopStorieDetail
}