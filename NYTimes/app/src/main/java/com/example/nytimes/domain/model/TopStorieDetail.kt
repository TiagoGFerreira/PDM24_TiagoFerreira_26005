package com.example.nytimes.domain.model

data class NewsDetailList(
    val news: List<NewsDetail>
)

data class NewsDetail(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val url: String,
    val image: String,
    val publish_date: String,
    val author: String,
)
