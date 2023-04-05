package com.example.football.data.room.dao

import androidx.room.*
import com.example.football.data.room.models.FootbalTableDateUpdateEntity
import com.example.football.data.room.models.FootballMatchImmediateEntity

@Dao
interface FootballTableUpdateDao {

    @Query("SELECT * FROM football_table_update")
    suspend fun getTime(): FootbalTableDateUpdateEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTime(competition: FootbalTableDateUpdateEntity)

    @Update
    suspend fun updateComp(date : FootbalTableDateUpdateEntity)

    @Delete
    suspend fun deleteTime(competition: FootbalTableDateUpdateEntity)

    @Query("DELETE FROM football_table_update")
    suspend fun deleteAllTime()
}