package com.gnova.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Articles")
data class ArticleDTO(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @Json(name = "author")
        val author: String? = "",
        @Json(name = "content")
        val content: String? = "",
        @Json(name = "description")
        val description: String? = "",
        @Json(name = "publishedAt")
        val publishedAt: String? = "",
        @Json(name = "source")
        val source: SourceDTO,
        @Json(name = "title")
        val title: String? = "",
        @Json(name = "url")
        val url: String? = "",
        @Json(name = "urlToImage")
        val urlToImage: String? = ""
)