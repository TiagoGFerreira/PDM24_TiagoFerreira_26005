package com.example.nytimes.domain.use_case



import com.example.nytimes.domain.model.NewsDetail
import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.repository.TopStorieRepository

class GetTopStoriesUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke(apikey: String, source : String, language : String) : List<NewsItem> {
        return repository.getTopStories(apikey,source,language)
    }
}

class GetTopStoriesDetailUseCase(private val repository: TopStorieRepository) {
    suspend operator fun invoke(topStorieId: Int, apikey: String) : NewsDetail {
        return repository.getTopStoriesDetail(topStorieId,apikey)
    }
}