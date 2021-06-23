package com.gnova.domain.models

import java.io.Serializable

data class Article(
        val author: Any? = null,
        val content: String = "",
        val description: String = "",
        val publishedAt: String = "",
        val source: String = "",
        val title: String = "",
        val url: String = "",
        val urlToImage: String = ""
) : Serializable