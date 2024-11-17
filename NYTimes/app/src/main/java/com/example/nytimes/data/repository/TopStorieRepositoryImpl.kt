package com.example.nytimes.data.repository

import com.example.nytimes.data.remote.api.TopStoriesApi
import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.model.TopStorieDetail
import com.example.nytimes.domain.repository.TopStorieRepository
import com.example.nytimes.data.remote.model.TopStorieDetailDTO
import org.intellij.lang.annotations.Language

class TopStorieRepositoryImpl (private val api: TopStoriesApi) : TopStorieRepository {
    override suspend fun getTopStories(): List<TopStorie> {
        return api.getTopStories("G1WZhzILeXYYS658VlOgrvD6jbR1Aa2H","us","en").map { it.toTopStorie() }
    }

    override suspend fun getTopStoriesDetail(topStorieId: String, language: String, srccountry: String): List<TopStorieDetail> {
        return api.getTopStorieDetail(topStorieId,language,srccountry).toTopStorieDetail()
    }
}