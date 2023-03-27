package com.example.football.repository

import com.example.football.data.host.SimpleApi
import com.example.football.data.host.matches.General_matches
import com.example.football.data.host.model.General
import com.example.football.data.host.table.GeneralTable
import com.example.football.data.host.team.TeamGeneral
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleApi: SimpleApi
) {
    suspend fun getCompetition(): Response<General> {
        return simpleApi.getCompetition()
    }
    suspend fun getMatchDay(): Response<General_matches> {
        return simpleApi.getMatchDay()
    }
    suspend fun getLigueMatch(): Response<General_matches> {
        return simpleApi.getLigueMatches()
    }
    suspend fun getLigueTable(ligue: String): Response<GeneralTable> {
        return simpleApi.getLigueTable(ligue)
    }
    suspend fun getMatch10Day(): Response<General_matches> {
        return simpleApi.getMatch10Day()
    }
    suspend fun getTeam(id: String): Response<TeamGeneral> {
        return simpleApi.getTeam(id)
    }
}