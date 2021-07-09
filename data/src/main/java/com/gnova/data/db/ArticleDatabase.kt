package com.gnova.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnova.data.models.ArticleDTO

@Database(entities = [ArticleDTO::class], version = 1, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}