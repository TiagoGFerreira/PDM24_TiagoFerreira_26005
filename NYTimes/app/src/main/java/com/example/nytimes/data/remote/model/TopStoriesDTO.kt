package com.example.nytimes.data.remote.model

data class TopStorieDto(
    val section : String,
    val subsection : String,
    val title : String,
    val abstract : String,
    val url : String,
    val byline : String
) {
    fun toTopStorie(): TopStorie {
        return TopStorie(section = section, subsection = subsection, title  = title,
            abstract = abstract, url = url, byline = byline)
    }
}