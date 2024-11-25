package com.example.nytimes.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.remote.api.RetrofitInstance
import com.example.nytimes.data.repository.TopStorieRepositoryImpl
import com.example.nytimes.domain.model.SingleNews
import com.example.nytimes.domain.use_case.GetTopStoriesDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopStorieDetailListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = TopStorieRepositoryImpl(api)
    private val getTopStorieDetailUseCase = GetTopStoriesDetailUseCase(repository)

    val topStorieDetail = MutableStateFlow<SingleNews?>(null)

    fun fetchTopStorieDetail(topStorieId: Int, apikey: String) {
        viewModelScope.launch {
            try {
                val stories = getTopStorieDetailUseCase(topStorieId, apikey)
                Log.d("TopStorieDetailViewModel", "Fetched story: $stories")
                topStorieDetail.value = stories
            } catch (e: Exception) {
                Log.e("TopStorieDetailViewModel", "Error fetching story details", e)
                topStorieDetail.value = null
            }
        }
    }

}

