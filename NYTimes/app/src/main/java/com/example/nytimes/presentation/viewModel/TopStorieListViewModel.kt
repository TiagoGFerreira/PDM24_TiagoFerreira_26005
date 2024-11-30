package com.example.nytimes.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.remote.api.RetrofitInstance
import com.example.nytimes.data.repository.TopStorieRepositoryImpl
import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.domain.use_case.GetTopStoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopStorieListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = TopStorieRepositoryImpl(api)
    private val getTopStorieUseCase = GetTopStoriesUseCase(repository)
    private val apikey = "e35283d0696b465bbd3cefc5ee783934"

    val topstorie = MutableStateFlow<List<NewsItem>>(emptyList())
    fun fetchTopStorie(source : String, language : String) {
        viewModelScope.launch {
                val stories = getTopStorieUseCase(apikey,source,language)
                topstorie.value = stories
        }
    }
}