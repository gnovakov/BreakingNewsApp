package com.gnova.data.db

import androidx.room.*
import com.gnova.data.models.ArticleDTO
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(articleDTO: ArticleDTO) : Completable

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): Single<List<ArticleDTO>>

    @Delete
    fun deleteArticle(articleDTO: ArticleDTO) : Completable

}