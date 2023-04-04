package com.example.football.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "football_match_day")
@Parcelize
data class FootballMatchDayEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var idmatch:Int? = null,
    var utcdate:Int? = null,
    var status:String? = null,
    var matchday:String? = null,
    var stage:String? = null,
    var hometeamid:String? = null,
    var hometeamname:String? = null,
    var hometeamshort:String? = null,
    var hometeamtle:String? = null,
    var hometeamcrest:String? = null,
    var awayteamid:String? = null,
    var awayteamname:String? = null,
    var awayteamshort:String? = null,
    var awayteamtle:String? = null,
    var seasonmatchday:String? = null,
    var competitionname:String? = null,
    var competitioncode:String? = null,
    var competitiontype:String? = null,
    var competitionemblem:String? = null,
    var areaid:String? = null,
    var areaname:String? = null,
    var areacode:String? = null,
    var areaflag:String? = null
)