package com.example.football.repository.usecase

import com.example.football.data.host.matches.MatchesItem
import com.example.football.data.room.models.FootballMatchImmediateEntity
import com.example.football.data.util.MapperMatches

class MapperMatchImmediateUseCase : MapperMatches<MatchesItem, FootballMatchImmediateEntity> {
    override fun mapFromMatchesEntity(entity: MatchesItem?): FootballMatchImmediateEntity {
        return FootballMatchImmediateEntity(
            id = 0,
            idmatch = entity?.id,
            utcdate = entity?.utcDate,
            status = entity?.status,
            matchday = entity?.matchday,
            stage = entity?.stage,
            hometeamid = entity?.homeTeam?.id,
            hometeamname = entity?.homeTeam?.name,
            hometeamshort = entity?.homeTeam?.shortName,
            hometeamtla = entity?.homeTeam?.tla,
            hometeamcrest = entity?.homeTeam?.crest,
            awayteamid = entity?.awayTeam?.id,
            awayteamname = entity?.awayTeam?.name,
            awayteamshort = entity?.awayTeam?.shortName,
            awayteamtla = entity?.awayTeam?.tla,
            awayteamcrest = entity?.awayTeam?.crest,
            seasonmatchday = entity?.season?.currentMatchday,
            competitionname = entity?.competition?.name,
            competitioncode = entity?.competition?.code,
            competitiontype = entity?.competition?.type,
            competitionemblem = entity?.competition?.emblem,
            areaid = entity?.area?.id,
            areaname = entity?.area?.name,
            areacode = entity?.area?.code,
            areaflag = entity?.area?.flag,
            scoreduration = entity?.score?.duration,
            scorewinner = entity?.score?.winner.toString(),
            scorehalftimehome = entity?.score?.halfTime?.home.toString(),
            scorehalftimeaway = entity?.score?.halfTime?.away.toString(),
            scorefulltimehome = entity?.score?.fullTime?.home.toString(),
            scorefulltimeaway = entity?.score?.fullTime?.away.toString()
        )
    }
}