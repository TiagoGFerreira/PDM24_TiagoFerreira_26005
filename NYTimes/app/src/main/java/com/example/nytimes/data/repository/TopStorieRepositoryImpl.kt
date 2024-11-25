package com.example.nytimes.data.repository

import android.util.Log
import com.example.nytimes.data.remote.api.TopStoriesApi
import com.example.nytimes.data.remote.model.TopStorieDetailDTO
import com.example.nytimes.domain.repository.TopStorieRepository
import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.model.SingleNews

class TopStorieRepositoryImpl (private val api: TopStoriesApi) : TopStorieRepository {
    override suspend fun getTopStories(): List<NewsItem> {
        try {
            val response = api.getTopStories("e35283d0696b465bbd3cefc5ee783934", "us", "en")
            val newsItems = response.top_news.flatMap { it.news }

            return newsItems
        } catch (e: Exception) {
            return emptyList()
        }
    }


    override suspend fun getTopStoriesDetail(topStorieId: Int, apikey: String): SingleNews {
        try {
            val response = api.getTopStorieDetail(topStorieId, apikey)

            val firstNews = response.news.firstOrNull()

            if (firstNews != null) {
                return firstNews.toTopStorieDetail()
            } else {
                throw Exception("No news found for the given ID.")
            }
        } catch (e: Exception) {
            Log.e("Error", "Error fetching details: ${e.message}")
            throw e
        }
    }



}