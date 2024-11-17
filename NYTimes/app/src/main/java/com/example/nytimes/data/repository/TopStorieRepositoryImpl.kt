package com.example.nytimes.data.repository

import com.example.nytimes.data.remote.api.TopStoriesApi
import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.repository.TopStorieRepository

abstract class CoinRepositoryImpl (private val api: TopStoriesApi) : TopStorieRepository {
    override suspend fun getTopStories(): List<TopStorie> {
        return api.getTopStories("G1WZhzILeXYYS658VlOgrvD6jbR1Aa2H").map { it.toTopStorie() }
    }
}