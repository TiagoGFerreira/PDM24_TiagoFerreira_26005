package com.example.nytimes.domain.use_case

import com.example.nytimes.domain.repository.TopStorieRepository

class GetTopStoriesUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke()
}