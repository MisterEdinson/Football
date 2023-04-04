package com.example.football.data.room.dao

import androidx.room.*
import com.example.football.data.room.models.FootballMatchImmediateEntity

@Dao
interface FootballMatchImmediateDao {
    @Query("SELECT * FROM football_match_day_immediate ORDER BY id")
    suspend fun getMatchImmediate(): List<FootballMatchImmediateEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatchImmediate(competition: List<FootballMatchImmediateEntity>)

    @Delete
    suspend fun deleteMatchImmediate(competition: FootballMatchImmediateEntity)

    @Query("DELETE FROM football_match_day_immediate")
    suspend fun deleteAllMatchImmediate()
}