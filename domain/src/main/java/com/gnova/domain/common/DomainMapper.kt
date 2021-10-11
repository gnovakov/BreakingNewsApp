package com.gnova.domain.common

interface DomainMapper <E, D> {

    fun mapToDomain(dto: E) : D

    fun mapToEntity(entity: D) : E

}