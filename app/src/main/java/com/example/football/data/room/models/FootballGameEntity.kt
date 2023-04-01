package com.example.football.data.room.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "football_game_lig_data")
@Parcelize
data class FootballGameEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var idgame:Int,
    var status:String,
    var matchday:String,
    var date:String,
    var dateupdateroom:String,
    var hometeamid:String,
    var hometeamname:String,
    var hometeamlabel:String,
    var awayteamid:String,
    var awayteamname:String,
    var awayteamlabel:String = "",
    var scorewinner:String = "",
    var scorefulltimehome:String = "",
    var scorefulltimeaway:String = "",
    var scorehalftimehome:String = "",
    var scorehalftimeaway:String = "",
) : Parcelable