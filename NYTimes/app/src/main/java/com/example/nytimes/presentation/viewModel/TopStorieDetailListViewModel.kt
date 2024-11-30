package com.example.nytimes.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.remote.api.RetrofitInstance
import com.example.nytimes.data.repository.TopStorieRepositoryImpl
import com.example.nytimes.domain.model.NewsDetail
import com.example.nytimes.domain.use_case.GetTopStoriesDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopStorieDetailListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = TopStorieRepositoryImpl(api)
    private val getTopStorieDetailUseCase = GetTopStoriesDetailUseCase(repository)
    private val apikey = "e35283d0696b465bbd3cefc5ee783934"

    val topStorieDetail = MutableStateFlow<NewsDetail?>(null)

    fun fetchTopStorieDetail(topStorieId: Int) {
        viewModelScope.launch {
                val stories = getTopStorieDetailUseCase(topStorieId, apikey)
                topStorieDetail.value = stories
        }
    }

}

