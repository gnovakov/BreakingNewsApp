package com.gnova.data.mappers

import com.gnova.data.models.ArticleDTO
import com.gnova.domain.common.DomainMapper
import com.gnova.domain.models.Article
import javax.inject.Inject

class ArticleDTOMapper @Inject constructor() : DomainMapper<ArticleDTO, Article> {

    override fun mapToDomain(entity: ArticleDTO): Article {
        return Article(
                author = entity.author,
                content = entity.content,
                description = entity.description,
                publishedAt = entity.publishedAt,
                source = entity.source.name,
                title = entity.title,
                url = entity.url,
                urlToImage = entity.urlToImage

        )
    }

    fun mapToDomainList(articleDTOs: List<ArticleDTO>): List<Article> {
        return articleDTOs.map {
            mapToDomain(it) }
    }

}