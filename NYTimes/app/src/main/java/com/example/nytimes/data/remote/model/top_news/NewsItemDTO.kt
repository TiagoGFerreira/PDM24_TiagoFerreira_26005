package com.example.nytimes.data.remote.model.top_news

import com.example.nytimes.domain.model.top_news.NewsItem

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