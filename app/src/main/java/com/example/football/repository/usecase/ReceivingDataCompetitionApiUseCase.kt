package com.example.football.repository.usecase

import com.example.football.data.host.model.CompetitionsItem
import com.example.football.data.host.model.General
import com.example.football.data.room.models.FootballLigsEntity
import retrofit2.Response

class ReceivingDataCompetitionApiUseCase{
    fun receivingDataApi(response: Response<General>) : List<FootballLigsEntity>{
        val competitions: List<CompetitionsItem?>? = response.body()?.competitions
        val mapper = MapperUseCase()
        val footballLigsEntities: List<FootballLigsEntity> = competitions?.mapNotNull { competition ->
            competition?.let { mapper.mapFromEntity(it) }
        } ?: emptyList()
        return footballLigsEntities
    }
}