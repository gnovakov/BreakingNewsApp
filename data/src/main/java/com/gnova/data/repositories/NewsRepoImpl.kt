package com.gnova.data.repositories

import com.gnova.data.api.NewsApi
import com.gnova.data.mappers.ArticleDTOMapper
import com.gnova.domain.models.Article
import com.gnova.domain.repositories.NewsRepo
import io.reactivex.Single
import javax.inject.Inject

class NewsRepoImpl@Inject constructor(
    private val newsApi: NewsApi,
    private val articleMapper: ArticleDTOMapper
    ) : NewsRepo {

    override fun getBreakingNews(country: String, page: Int): Single<List<Article>> {

        return newsApi.getBreakingNews(country, page)
                .map {
                    articleMapper.mapToDomainList(it.articles)
                }

    }

    override fun searchNews(searchQuery: String, page: Int): Single<List<Article>> {

        return newsApi.searchNews(searchQuery)
                .map {
                    articleMapper.mapToDomainList(it.articles)
                }

    }

}