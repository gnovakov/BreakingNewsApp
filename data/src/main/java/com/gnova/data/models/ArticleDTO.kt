package com.gnova.data.models

import com.squareup.moshi.Json

data class ArticleDTO(
        @Json(name = "author")
        val author: Any,
        @Json(name = "content")
        val content: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "publishedAt")
        val publishedAt: String,
        @Json(name = "source")
        val source: SourceDTO,
        @Json(name = "title")
        val title: String,
        @Json(name = "url")
        val url: String,
        @Json(name = "urlToImage")
        val urlToImage: String
)