package com.example.football.repository.usecase

import com.example.football.data.host.model.CompetitionsItem
import com.example.football.data.room.models.FootballLigsEntity
import com.example.football.data.util.Mapper
import java.util.*

class MapperUseCase : Mapper<CompetitionsItem, FootballLigsEntity> {

    override fun mapFromEntity(entity: CompetitionsItem?): FootballLigsEntity {
        return FootballLigsEntity(
            id = 0,
            idarea = entity?.area?.id,
            idligue = entity?.id,
            areacode = entity?.area?.code,
            areaname = entity?.area?.name,
            name = entity?.name,
            code = entity?.code,
            type = entity?.type,
            emblem = entity?.emblem,
            currentmatchday = entity?.currentSeason?.currentMatchday,
            currentwinner = entity?.currentSeason?.winner.toString(),
            dateUpdate = Date().time
        )
    }
}


