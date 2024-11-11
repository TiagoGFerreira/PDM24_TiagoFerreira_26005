package com.example.nytimes.domain.repository

import com.example.nytimes.data.remote.model.TopStorie

interface TopStorieRepository {
    suspend fun getTopStories(): List<TopStorie>
}