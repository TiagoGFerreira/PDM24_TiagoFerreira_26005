package com.example.nytimes.domain.model

data class SingleNewsItem(
    val news: List<SingleNews>
)

data class SingleNews(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val url: String,
    val image: String,
    val publish_date: String,
    val author: String,
)
