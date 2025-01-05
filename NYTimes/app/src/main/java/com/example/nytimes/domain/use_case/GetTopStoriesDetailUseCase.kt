package com.example.nytimes.domain.use_case

import com.example.nytimes.domain.model.single_news.NewsDetail
import com.example.nytimes.domain.repository.TopStorieRepository

class GetTopStoriesDetailUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke(topStorieId: Int, apikey: String) : NewsDetail {
        return repository.getTopStoriesDetail(topStorieId,apikey)
    }
}