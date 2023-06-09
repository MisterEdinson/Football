package com.example.football.data.room.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "football_table_update")
@Parcelize
data class FootbalTableDateUpdateEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var competition: String? = null,
    var timeday: String? = null,
    var timenext: String? = null
) : Parcelable