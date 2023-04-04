package com.example.football.data.util

import com.example.football.data.host.matches.MatchesItem

interface MapperMatches<Entity, DomainModel> {
    fun mapFromMatchesEntity(entity: MatchesItem?): DomainModel
}