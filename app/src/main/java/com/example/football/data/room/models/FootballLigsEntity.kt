package com.example.football.data.room.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "football_competition")
@Parcelize
data class FootballLigsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idligue: Int? = null,
    var idarea: Int? = null,
    var areacode: String? = null,
    var areaname: String? = null,
    var name: String? = null,
    var code: String? = null,
    var type: String? = null,
    var emblem: String? = null,
    var currentmatchday: Int? = null,
    var currentwinner: String? = null,
    var dateUpdate: Long?
) : Parcelable
