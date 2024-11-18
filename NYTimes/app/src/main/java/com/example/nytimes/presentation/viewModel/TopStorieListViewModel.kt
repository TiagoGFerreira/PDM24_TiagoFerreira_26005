package com.example.nytimes.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.remote.api.RetrofitInstance
import com.example.nytimes.data.repository.TopStorieRepositoryImpl
import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.use_case.GetTopStoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopStorieListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = TopStorieRepositoryImpl(api)
    private val getTopStorieUseCase = GetTopStoriesUseCase(repository)

    val topstorie = MutableStateFlow<List<TopStorie>>(emptyList())

    fun fetchTopStorie() {
        viewModelScope.launch {
            try {
                topstorie.value = getTopStorieUseCase()
            } catch (e: Exception) {
                topstorie.value = emptyList()
            }
        }
    }
}