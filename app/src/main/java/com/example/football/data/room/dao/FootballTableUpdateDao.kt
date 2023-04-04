package com.example.football.data.room.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FootballTableUpdateDao {
    @Query("UPDATE football_table_update SET timeupdatecompetition = :date WHERE id = 0")
    suspend fun updateCompetitions(date:Long)

    @Query("UPDATE football_table_update SET timeupdatematchday = :date WHERE id = 0")
    suspend fun updateMatchDay(date:Long)
}