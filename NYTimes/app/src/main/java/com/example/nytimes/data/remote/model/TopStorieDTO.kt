package com.example.nytimes.data.remote.model

import com.example.nytimes.domain.model.TopNewsResponse
import com.example.nytimes.domain.model.TopNewsItem
import com.example.nytimes.domain.model.NewsItem


data class NewsItemDTO(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String
) {

    fun toNewsItem(): NewsItem {
        return NewsItem(id = id, title = title, summary = summary, author = author)
    }
}

data class TopNewsItemDTO(
    val news: List<NewsItemDTO>
) {

    fun toTopNewsItem(): TopNewsItem {
        return TopNewsItem(
            news = news.map { it.toNewsItem() }
        )
    }
}

data class TopNewsResponseDTO(
    val top_news: List<TopNewsItemDTO>
) {
    fun toTopNewsResponse(): TopNewsResponse {
        return TopNewsResponse(
            top_news = top_news.map { it.toTopNewsItem() }
        )
    }
}