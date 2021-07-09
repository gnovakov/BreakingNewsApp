package com.gnova.data.db

import androidx.room.*
import com.gnova.data.models.ArticleDTO

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(article: ArticleDTO): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<ArticleDTO>

    @Delete
    fun deleteArticle(article: ArticleDTO)

}