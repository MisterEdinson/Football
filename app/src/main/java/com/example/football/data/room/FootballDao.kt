package com.example.football.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football.data.room.models.FootballGameEntity

@Dao
interface FootballDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameLig(dataGameLigEntity: FootballGameEntity)

    @Query("SELECT * FROM football_game_lig_data ORDER BY id ASC")
    fun getAllGameLig(): LiveData<List<FootballGameEntity>>
}