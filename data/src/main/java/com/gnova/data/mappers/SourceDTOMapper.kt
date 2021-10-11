package com.gnova.data.mappers

import com.gnova.data.models.SourceDTO
import com.gnova.domain.common.DomainMapper
import com.gnova.domain.models.Source
import javax.inject.Inject

class SourceDTOMapper @Inject constructor() : DomainMapper<SourceDTO, Source> {

    override fun mapToDomain(dto: SourceDTO): Source {
        return Source(
                id = dto.id,
                name = dto.name

        )
    }

    override fun mapToEntity(entity: Source): SourceDTO {
        return SourceDTO(
            id = entity.id,
            name = entity.name

        )
    }

    fun mapToDomainList(SourceDTOs: List<SourceDTO>): List<Source> {
        return SourceDTOs.map {
            mapToDomain(it) }
    }



}