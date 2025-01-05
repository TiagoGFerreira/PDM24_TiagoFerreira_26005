package com.example.nytimes.data.remote.model.top_news

import com.example.nytimes.domain.model.top_news.TopNewsItem

data class TopNewsItemDTO(
    val news: List<NewsItemDTO>
) {

    fun toTopNewsItem(): TopNewsItem {
        return TopNewsItem(
            news = news.map { it.toNewsItem() }
        )
    }
}