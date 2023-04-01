package com.example.football.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.football.data.room.models.FootballGameEntity

@Database(entities = [FootballGameEntity::class], version = 1, exportSchema = false)
abstract class FootballDB : RoomDatabase() {
    abstract fun footballDao() : FootballDao
    companion object{
        @Volatile
        private var INSTANCE : FootballDB? = null
        fun getDataBase(context: Context) : FootballDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootballDB::class.java,
                    "football_game_lig_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}