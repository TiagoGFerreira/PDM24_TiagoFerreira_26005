package com.example.nytimes.data.repository

import com.example.nytimes.data.remote.api.TopStoriesApi
import com.example.nytimes.domain.repository.TopStorieRepository
import com.example.nytimes.domain.model.NewsDetail
import com.example.nytimes.domain.model.NewsItem


class TopStorieRepositoryImpl (private val api: TopStoriesApi) : TopStorieRepository {
    override suspend fun getTopStories(apikey: String, source : String, language : String): List<NewsItem> {
            val response = api.getTopStories(apikey,source,language)
            val newsItems = response.top_news.flatMap { it.news }
            return newsItems
    }


    override suspend fun getTopStoriesDetail(topStorieId: Int, apikey: String): NewsDetail {
            val response = api.getTopStorieDetail(topStorieId, apikey)
            val firstNews = response.news.firstOrNull()

            if (firstNews != null) {
                return firstNews.toTopStorieDetail()
            } else {
                throw Exception("No news found")
            }
    }
}