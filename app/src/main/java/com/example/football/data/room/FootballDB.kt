package com.example.football.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.football.data.room.dao.FootballLigsDao
import com.example.football.data.room.models.FootballLigsEntity

@Database(
    entities = [FootballLigsEntity::class],
    version = 1,
    exportSchema = false)
abstract class FootballDB : RoomDatabase() {
    abstract fun footballLigsDao() : FootballLigsDao
}