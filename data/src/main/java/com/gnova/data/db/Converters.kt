package com.gnova.data.db

import androidx.room.TypeConverter
import com.gnova.data.models.SourceDTO

class Converters {

    @TypeConverter
    fun fromSource(source: SourceDTO): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): SourceDTO {
        return SourceDTO(name, name)
    }
}