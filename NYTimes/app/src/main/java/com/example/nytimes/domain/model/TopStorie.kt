package com.example.nytimes.domain.model

data class TopNewsResponse(
    val top_news: List<TopNewsItem>
)

data class TopNewsItem(
    val news: List<NewsItem>
)

data class NewsItem(
    val id: Int,
    val title: String,
    val text: String?,
    val summary: String?
)
