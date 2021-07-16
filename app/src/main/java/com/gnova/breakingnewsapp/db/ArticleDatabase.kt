package com.gnova.breakingnewsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gnova.data.db.ArticleDao
import com.gnova.data.db.Converters
import com.gnova.data.models.ArticleDTO

@Database(entities = [ArticleDTO::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}