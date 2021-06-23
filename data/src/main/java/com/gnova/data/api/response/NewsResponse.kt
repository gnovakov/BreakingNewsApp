package com.gnova.data.api.response

import com.gnova.data.models.ArticleDTO
import com.squareup.moshi.Json

data class NewsResponse(
        @Json(name = "articles")
        val articles: List<ArticleDTO>,
        @Json(name = "status")
        val status: String,
        @Json(name = "totalResults")
        val totalResults: Int
)