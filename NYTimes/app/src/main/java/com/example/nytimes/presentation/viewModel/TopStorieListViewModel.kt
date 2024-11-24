package com.example.nytimes.presentation.viewModel

import android.util.Log
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

    val topstorie = MutableStateFlow<List<NewsItem>>(emptyList())
    fun fetchTopStorie() {
        viewModelScope.launch {
            try {
                val stories = getTopStorieUseCase()
                topstorie.value = stories
            } catch (e: Exception) {
                topstorie.value = emptyList()
            }
        }
    }
}