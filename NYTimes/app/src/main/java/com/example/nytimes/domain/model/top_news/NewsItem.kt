package com.example.nytimes.domain.model.top_news

data class NewsItem(
    val id: Int,
    val title: String,
    val summary: String,
    val author: String
)