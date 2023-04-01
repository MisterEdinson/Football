package com.example.football.data.host

import com.example.football.data.host.matches.General_matches
import com.example.football.data.host.model.General
import com.example.football.data.host.table.GeneralTable
import com.example.football.data.host.team.TeamGeneral
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    //все доступные лиги соревнований
    @GET("competitions")
    suspend fun getCompetition(): Response<General>

    //все доступные сегодняшние матчи
    @GET("matches")
    suspend fun getMatchDay(): Response<General_matches>

    //предстоящие матчи до 10 дней вперед по выбранным лигам
    @GET("matches")
    suspend fun getMatch10Day(
        @Query("dateTo") dataTo: String,
        @Query("dateFrom") dataFrom: String
    ): Response<General_matches>

    //все доступные предстоящие матчи RealMadrid
    @GET("teams/86/matches?status=SCHEDULED")
    suspend fun getMatchesRealMadrid(): Response<General>

    //все доступные предстоящие матчи PremierLigue
    @GET("competitions/PL/matches?status=SCHEDULED&matchday=29")
    suspend fun getLigueMatches(): Response<General_matches>

    //все доступные предстоящие матчи PremierLigue
    @GET("competitions/{ligue}/standings")
    suspend fun getLigueTable(
        @Path("ligue") ligue: String
    ): Response<GeneralTable>

    @GET("teams/{idTeam}")
    suspend fun getTeam(
        @Path("idTeam") id: String
    ): Response<TeamGeneral>

}

