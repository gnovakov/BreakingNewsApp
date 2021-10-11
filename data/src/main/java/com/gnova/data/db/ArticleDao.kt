package com.gnova.data.db

import androidx.room.*
import com.gnova.data.models.ArticleDTO


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(articleDTO: ArticleDTO)

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): List<ArticleDTO>

    @Delete
    fun deleteArticle(articleDTO: ArticleDTO)

}