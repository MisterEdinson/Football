package com.example.football.repository.usecase

import com.example.football.data.host.matches.GeneralMatches
import com.example.football.data.host.matches.MatchesItem
import com.example.football.data.room.models.FootballMatchImmediateEntity
import retrofit2.Response

class ReceivingDataMatchImmediateUseCase {
    fun receivingMatchesImmediateApi(response: Response<GeneralMatches>): List<FootballMatchImmediateEntity> {
        val matches: List<MatchesItem?>? = response.body()?.matches
        val mapper = MapperMatchImmediateUseCase()
        val footballMatchEntities: List<FootballMatchImmediateEntity> =
            matches?.mapNotNull { match ->
                match?.let { mapper.mapFromMatchesEntity(match) }
            } ?: emptyList()
        return footballMatchEntities
    }
}