package com.example.football.repository

import com.example.football.data.host.SimpleApi
import com.example.football.data.host.matches.GeneralMatches
import com.example.football.data.host.table.GeneralTable
import com.example.football.data.host.team.TeamGeneral
import com.example.football.data.room.dao.FootballLigsDao
import com.example.football.data.room.dao.FootballMatchDayDao
import com.example.football.data.room.dao.FootballTableUpdateDao
import com.example.football.data.room.models.FootballLigsEntity
import com.example.football.data.room.models.FootballMatchesDayEntity
import com.example.football.repository.usecase.ReceivingDataCompetitionApiUseCase
import com.example.football.repository.usecase.ReceivingDataMatchUseCase
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleApi: SimpleApi,
    private val footballLeagueDao: FootballLigsDao,
    private val footballMatchDayDao: FootballMatchDayDao,
    private val fooballTimeUpdate: FootballTableUpdateDao
) {
    //1. получить все соревнования с API+
    //2. получить все сегодняшние матчи с API+
    //3. получить ближайшие матчи с API+
    //4. записать все соревнования в Room
    //5. записать все сегодняшние матчи в Room
    //6. записать все ближайшие матчи в Room
    //7. вернуть доступные соревнования
    //8. вернуть сегодняшние матчи
    //9. вернуть ближайшие соревнования
    var today = Date().time // seconds
    //1
    suspend fun getCompetition(): List<FootballLigsEntity> {
        //receiving data API and conversion
        footballLeagueDao.deleteAllCompetitionLeague()
        val competitionDao = footballLeagueDao.getCompetitionLeague()
        when(competitionDao.size){
            0->{
                val competitions = ReceivingDataCompetitionApiUseCase().receivingDataApi(simpleApi.getCompetition())
                footballLeagueDao.addCompetitionLeague(competitions)
                fooballTimeUpdate.updateCompetitions(today)
                return footballLeagueDao.getCompetitionLeague()
            }
            else->{
                return footballLeagueDao.getCompetitionLeague()
            }
        }
    }
    //2
    suspend fun getMatchDay(): List<FootballMatchesDayEntity> {
        val matchDayDao = footballMatchDayDao.getMatchDay()
        when(matchDayDao.isEmpty()){
            true->{
                val matchDay = ReceivingDataMatchUseCase().receivingMatchesApi(simpleApi.getMatchDay())
                footballMatchDayDao.addMatchDay(matchDay)
                fooballTimeUpdate.updateMatchDay(today)
                return footballMatchDayDao.getMatchDay()
            }
            else->{
                return footballMatchDayDao.getMatchDay()
            }
        }
    }
    //3
    suspend fun getMatch10Day(dataTo: String, dataFrom: String): Response<GeneralMatches> {
        return simpleApi.getMatch10Day(dataTo, dataFrom)
    }

    suspend fun getLigueTable(ligue: String): Response<GeneralTable> {
        return simpleApi.getLigueTable(ligue)
    }

    suspend fun getTeam(id: String): Response<TeamGeneral> {
        return simpleApi.getTeam(id)
    }
}