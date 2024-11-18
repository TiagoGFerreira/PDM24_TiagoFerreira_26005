package com.example.nytimes.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.remote.api.RetrofitInstance
import com.example.nytimes.data.repository.TopStorieRepositoryImpl
import com.example.nytimes.domain.model.TopStorieDetail
import com.example.nytimes.domain.use_case.GetTopStoriesDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopStorieDetailListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = TopStorieRepositoryImpl(api)
    private val getTopStorieDetailUseCase = GetTopStoriesDetailUseCase(repository)

    val topStorieDetail = MutableStateFlow<TopStorieDetail?>(null)

    fun fetchTopStorieDetail(topStorieId: String, language: String, srccountry: String) {
        viewModelScope.launch {
            try {
                topStorieDetail.value = getTopStorieDetailUseCase(topStorieId, language, srccountry)
            } catch (e: Exception) {
                topStorieDetail.value = null
            }
        }
    }
}
