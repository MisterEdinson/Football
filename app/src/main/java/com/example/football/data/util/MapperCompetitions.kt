package com.example.football.data.util

import com.example.football.data.host.model.CompetitionsItem

interface MapperCompetitions<Entity, DomainModel> {
    fun mapFromFootballCompetitionsEntity(entity: CompetitionsItem?): DomainModel
}