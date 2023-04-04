package com.example.football.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.football.data.room.dao.FootballLigsDao
import com.example.football.data.room.dao.FootballMatchDayDao
import com.example.football.data.room.dao.FootballMatchImmediateDao
import com.example.football.data.room.dao.FootballTableUpdateDao
import com.example.football.data.room.models.FootbalTableDateUpdateEntity
import com.example.football.data.room.models.FootballLigsEntity
import com.example.football.data.room.models.FootballMatchImmediateEntity
import com.example.football.data.room.models.FootballMatchesDayEntity

@Database(
    entities = [
        FootballLigsEntity::class,
        FootballMatchesDayEntity::class,
        FootbalTableDateUpdateEntity::class,
        FootballMatchImmediateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FootballDB : RoomDatabase() {
    abstract fun footballLigsDao(): FootballLigsDao
    abstract fun footballMatchDayDao(): FootballMatchDayDao
    abstract fun footballUpadateTableDao(): FootballTableUpdateDao
    abstract fun footballMatchImmediateDao(): FootballMatchImmediateDao
}