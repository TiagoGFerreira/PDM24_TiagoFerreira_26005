package com.example.nytimes.domain.use_case

import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.model.TopStorieDetail
import com.example.nytimes.domain.repository.TopStorieRepository

class GetTopStoriesUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke() : List<TopStorie> {
        return repository.getTopStories()
    }
}

class GetTopStoriesDetailUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke(topStorieId: String, language: String, srccountry: String) : TopStorieDetail? {
        return repository.getTopStoriesDetail(topStorieId,language,srccountry)
    }}