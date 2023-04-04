package com.example.football.repository

import com.example.football.data.host.SimpleApi
import com.example.football.data.host.table.GeneralTable
import com.example.football.data.host.team.TeamGeneral
import com.example.football.data.room.dao.FootballLigsDao
import com.example.football.data.room.dao.FootballMatchDayDao
import com.example.football.data.room.dao.FootballMatchImmediateDao
import com.example.football.data.room.dao.FootballTableUpdateDao
import com.example.football.data.room.models.FootballLigsEntity
import com.example.football.data.room.models.FootballMatchImmediateEntity
import com.example.football.data.room.models.FootballMatchesDayEntity
import com.example.football.repository.usecase.ReceivingDataCompetitionApiUseCase
import com.example.football.repository.usecase.ReceivingDataMatchImmediateUseCase
import com.example.football.repository.usecase.ReceivingDataMatchUseCase
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleApi: SimpleApi,
    private val footballLeagueDao: FootballLigsDao,
    private val footballMatchDayDao: FootballMatchDayDao,
    private val footballTimeUpdate: FootballTableUpdateDao,
    private val footballMatchImmediateDao: FootballMatchImmediateDao,
) {

    var today = Date().time // time in seconds

    suspend fun getCompetition(): List<FootballLigsEntity> {
        //receiving data API and conversion
        //footballLeagueDao.deleteAllCompetitionLeague()
        val competitionDao = footballLeagueDao.getCompetitionLeague()
        when (competitionDao.size) {
            0 -> {
                val competitions =
                    ReceivingDataCompetitionApiUseCase().receivingDataApi(simpleApi.getCompetition())
                footballLeagueDao.addCompetitionLeague(competitions)
                footballTimeUpdate.updateCompetitions(today)
                return footballLeagueDao.getCompetitionLeague()
            }
            else -> {
                return footballLeagueDao.getCompetitionLeague()
            }
        }
    }

    suspend fun getMatchDay(): List<FootballMatchesDayEntity> {
        //footballMatchDayDao.deleteAllMatchDay()
        val matchDayDao = footballMatchDayDao.getMatchDay()
        when (matchDayDao.isEmpty()) {
            true -> {
                val matchDay =
                    ReceivingDataMatchUseCase().receivingMatchesApi(simpleApi.getMatchDay())
                footballMatchDayDao.addMatchDay(matchDay)
                footballTimeUpdate.updateMatchDay(today)
                return footballMatchDayDao.getMatchDay()
            }
            else -> {
                return footballMatchDayDao.getMatchDay()
            }
        }
    }

    suspend fun getMatch10Day(
        dataTo: String,
        dataFrom: String
    ): List<FootballMatchImmediateEntity> {
        //footballMatchImmediateDao.deleteAllMatchImmediate()
        val matchDayDao = footballMatchImmediateDao.getMatchImmediate()
        when (matchDayDao.isEmpty()) {
            true -> {
                val matchDay = ReceivingDataMatchImmediateUseCase().receivingMatchesImmediateApi(
                    simpleApi.getMatch10Day(
                        dataTo,
                        dataFrom
                    )
                )
                footballMatchImmediateDao.addMatchImmediate(matchDay)
                footballTimeUpdate.updateMatchDay(today)
                return footballMatchImmediateDao.getMatchImmediate()
            }
            else -> {
                return footballMatchImmediateDao.getMatchImmediate()
            }
        }
    }

    suspend fun getLigueTable(ligue: String): Response<GeneralTable> {
        return simpleApi.getLigueTable(ligue)
    }

    suspend fun getTeam(id: String): Response<TeamGeneral> {
        return simpleApi.getTeam(id)
    }
}