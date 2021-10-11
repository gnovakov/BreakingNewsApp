package com.gnova.data.mappers

import com.gnova.data.models.ArticleDTO
import com.gnova.domain.common.DomainMapper
import com.gnova.domain.models.Article
import javax.inject.Inject

class ArticleDTOMapper @Inject constructor(
    private val sourceDTOMapper: SourceDTOMapper
) : DomainMapper<ArticleDTO, Article> {

    override fun mapToDomain(dto: ArticleDTO): Article {
        return Article(
                author = dto.author,
                content = dto.content,
                description = dto.description,
                publishedAt = dto.publishedAt,
                source = sourceDTOMapper.mapToDomain(dto.source),
                title = dto.title,
                url = dto.url,
                urlToImage = dto.urlToImage

        )
    }

    override fun mapToEntity(entity: Article): ArticleDTO {
        return ArticleDTO(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            publishedAt = entity.publishedAt,
            source = sourceDTOMapper.mapToEntity(entity.source),
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