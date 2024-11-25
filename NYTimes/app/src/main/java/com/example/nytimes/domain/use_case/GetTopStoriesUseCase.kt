package com.example.nytimes.domain.use_case

import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.model.SingleNews
import com.example.nytimes.domain.repository.TopStorieRepository

class GetTopStoriesUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke() : List<NewsItem> {
        return repository.getTopStories()
    }
}

class GetTopStoriesDetailUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke(topStorieId: Int, apikey: String) : SingleNews {
        return repository.getTopStoriesDetail(topStorieId,apikey)
    }
}