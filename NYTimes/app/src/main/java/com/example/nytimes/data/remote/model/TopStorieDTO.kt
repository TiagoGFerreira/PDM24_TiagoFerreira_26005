package com.example.nytimes.data.remote.model

import com.example.nytimes.domain.model.TopStorie

data class TopStorieDTO(
    val id : Int,
    val title : String,
    val text : String,
    val summary : String,
) {
    fun toTopStorie(): TopStorie {
        return TopStorie(id = id, title = title, text  = text,
            summary = summary)
    }
}