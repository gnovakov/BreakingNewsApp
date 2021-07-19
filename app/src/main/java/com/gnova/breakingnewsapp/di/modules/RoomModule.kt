package com.gnova.breakingnewsapp.di.modules

import android.app.Application
import androidx.room.Room
import com.gnova.breakingnewsapp.db.ArticleDatabase
import com.gnova.breakingnewsapp.db.ArticleDatabase.Companion.DATABASE_NAME
import com.gnova.data.db.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class RoomModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideArticleDb(): ArticleDatabase {
        return Room
            .databaseBuilder(app, ArticleDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideMessageDao(db: ArticleDatabase): ArticleDao {
        return db.getArticleDao()
    }
}
