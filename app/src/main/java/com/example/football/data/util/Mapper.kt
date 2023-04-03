package com.example.football.data.util

import com.example.football.data.host.model.CompetitionsItem

interface Mapper<Entity, DomainModel> {
    fun mapFromEntity(entity: CompetitionsItem?): DomainModel
}