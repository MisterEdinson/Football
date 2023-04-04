package com.example.football.data.room.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "football_match_day")
@Parcelize
data class FootballMatchesDayEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var idmatch:Int? = null,
    var utcdate:String? = null,
    var status:String? = null,
    var matchday:Int? = null,
    var stage:String? = null,
    var hometeamid:Int? = null,
    var hometeamname:String? = null,
    var hometeamshort:String? = null,
    var hometeamtla:String? = null,
    var hometeamcrest:String? = null,
    var awayteamid:Int? = null,
    var awayteamname:String? = null,
    var awayteamshort:String? = null,
    var awayteamtla:String? = null,
    var awayteamcrest:String? = null,
    var seasonmatchday:Int? = null,
    var competitionname:String? = null,
    var competitioncode:String? = null,
    var competitiontype:String? = null,
    var competitionemblem:String? = null,
    var areaid:Int? = null,
    var areaname:String? = null,
    var areacode:String? = null,
    var areaflag:String? = null,
    var scorewinner:String? = null,
    var scoreduration:String? = null,
    var scorefulltimehome:String? = null,
    var scorefulltimeaway:String? = null,
    var scorehalftimehome:String? = null,
    var scorehalftimeaway:String? = null,
): Parcelable