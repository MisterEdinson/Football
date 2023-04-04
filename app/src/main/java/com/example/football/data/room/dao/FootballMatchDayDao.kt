package com.example.football.data.room.dao

import androidx.room.*
import com.example.football.data.room.models.FootballMatchesDayEntity

@Dao
interface FootballMatchDayDao {
    @Query("SELECT * FROM football_match_day ORDER BY id")
    suspend fun getMatchDay(): List<FootballMatchesDayEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatchDay(competition: List<FootballMatchesDayEntity>)

    @Delete
    suspend fun deleteMatchDay(competition: FootballMatchesDayEntity)

    @Query("DELETE FROM football_match_day")
    suspend fun deleteAllMatchDay()
}