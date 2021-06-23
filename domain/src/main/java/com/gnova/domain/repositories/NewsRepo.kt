package com.gnova.domain.repositories

import com.gnova.domain.models.Article
import io.reactivex.Single

interface NewsRepo {

    fun getBreakingNews(country: String, page: Int): Single<List<Article>>

}