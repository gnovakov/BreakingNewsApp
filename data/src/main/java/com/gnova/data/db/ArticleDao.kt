package com.gnova.data.db

import androidx.room.*
import com.gnova.data.models.ArticleDTO
import io.reactivex.Completable

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(article: ArticleDTO): Completable

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): List<ArticleDTO>

    @Delete
    fun deleteArticle(article: ArticleDTO)

}