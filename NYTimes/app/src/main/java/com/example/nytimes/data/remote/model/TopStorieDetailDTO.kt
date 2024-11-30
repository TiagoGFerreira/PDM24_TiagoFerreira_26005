package com.example.nytimes.data.remote.model

import com.example.nytimes.domain.model.NewsDetail

data class TopStorieDetailDTO(
    val id: Int,
    val title: String,
    val text: String,
    val summary: String,
    val url: String,
    val image: String,
    val publish_date: String,
    val author: String,
) {
    fun toTopStorieDetail(): NewsDetail {
        return NewsDetail(
            id = id,
            title = title,
            text = text,
            summary = summary,
            url = url,
            image = image,
            publish_date = publish_date,
            author = author
        )
    }
}

data class SingleNewsItemDTO(
    val news: List<TopStorieDetailDTO>
)
