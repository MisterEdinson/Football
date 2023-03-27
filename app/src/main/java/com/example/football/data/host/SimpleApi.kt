package com.example.football.data.host

import com.example.football.data.host.model.General
import com.example.football.data.host.matches.General_matches
import com.example.football.data.host.news.NewsGeneral
import com.example.football.data.host.table.GeneralTable
import com.example.football.data.host.team.TeamGeneral

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    //все доступные лиги соревнований
    @GET("competitions")
    suspend fun getCompetition(): Response<General>

    //все доступные сегодняшние матчи
    @GET("matches")
    suspend fun getMatchDay(): Response<General_matches>

    //предстоящие матчи на 10 дней вперед по выбранным лигам
    @GET("matches?dateTo=2023-04-05&dateFrom=2023-03-27")
    suspend fun getMatch10Day(): Response<General_matches>

    //все доступные предстоящие матчи RealMadrid
    @GET("teams/86/matches?status=SCHEDULED")
    suspend fun getMatchesRealMadrid(): Response<General>

    //все доступные предстоящие матчи PremierLigue
    @GET("competitions/PL/matches?status=SCHEDULED&matchday=29")
    suspend fun getLigueMatches(): Response<General_matches>

    //все доступные предстоящие матчи PremierLigue
    @GET("competitions/{ligue}/standings")
    suspend fun getLigueTable(
        @Path("ligue") ligue:String
    ): Response<GeneralTable>

    @GET("teams/{idTeam}")
    suspend fun getTeam(
        @Path("idTeam") id:String
    ): Response<TeamGeneral>

    //Последние новости спорта
    @GET("https://newsapi.org/v2/top-headlines?country=ru&apiKey=fafe7f309c354670bfb0afc5f5ce0204&category=sports&pageSize=10&q\n" +
            "=футбол")
    suspend fun getNews(): Response<NewsGeneral>
}

