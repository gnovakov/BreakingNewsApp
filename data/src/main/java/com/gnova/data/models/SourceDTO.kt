package com.gnova.data.models

import com.squareup.moshi.Json

data class SourceDTO(
        @Json(name = "id")
        val id: Any,
        @Json(name = "name")
        val name: String
)