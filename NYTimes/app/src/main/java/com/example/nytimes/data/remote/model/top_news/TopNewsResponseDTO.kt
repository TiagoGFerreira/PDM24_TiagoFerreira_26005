package com.example.nytimes.data.remote.model.top_news

import com.example.nytimes.domain.model.top_news.TopNewsResponse

data class TopNewsResponseDTO(
    val top_news: List<TopNewsItemDTO>
) {
    fun toTopNewsResponse(): TopNewsResponse {
        return TopNewsResponse(
            top_news = top_news.map { it.toTopNewsItem() }
        )
    }
}