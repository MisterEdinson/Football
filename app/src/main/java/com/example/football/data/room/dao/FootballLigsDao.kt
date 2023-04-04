package com.example.football.data.room.dao

import androidx.room.*
import com.example.football.data.room.models.FootballLigsEntity

@Dao
interface FootballLigsDao {
    @Query("SELECT * FROM football_competition ORDER BY idligue ASC")
    suspend fun getCompetitionLeague(): List<FootballLigsEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompetitionLeague(competition: List<FootballLigsEntity>)
    @Delete
    suspend fun deleteCompetitionLeague(competition: FootballLigsEntity)
    @Query("DELETE FROM football_competition")
    suspend fun deleteAllCompetitionLeague()
}