package com.example.nytimes.data.remote.model

import com.example.nytimes.domain.model.TopStorie
import com.example.nytimes.domain.model.TopStorieDetail

data class TopStorieDetailDTO(
    val id : Int,
    val title : String,
    val text : String,
    val summary : String,
){
    fun toTopStorieDetail(): TopStorieDetail {
        return TopStorieDetail(id = id, title = title, text  = text,
            summary = summary)
    }
}
