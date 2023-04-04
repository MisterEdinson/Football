package com.example.football.repository.usecase

import com.example.football.data.host.matches.GeneralMatches
import com.example.football.data.host.matches.MatchesItem
import com.example.football.data.room.models.FootballMatchesDayEntity
import retrofit2.Response

class ReceivingDataMatchUseCase {
    fun receivingMatchesApi(response: Response<GeneralMatches>) : List<FootballMatchesDayEntity>{
        val matches: List<MatchesItem?>? = response.body()?.matches
        val mapper = MapperMatchesUseCase()
        val footballMatchEntities : List<FootballMatchesDayEntity> = matches?.mapNotNull { match ->
            match?.let { mapper.mapFromMatchesEntity(match) }
        } ?: emptyList()
        return footballMatchEntities
    }
}