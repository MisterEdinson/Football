package com.example.football.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.football.data.room.models.FootballGameEntity

@Database(entities = [FootballGameEntity::class], version = 1, exportSchema = false)
abstract class FootballDB : RoomDatabase() {
    abstract fun footballDao() : FootballDao
}