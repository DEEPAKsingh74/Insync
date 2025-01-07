package com.example.insync.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.insync.data.model.ScheduleEntity

@Database(entities = [ScheduleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    // Define all DAO related to this database here..
    abstract  fun userScheduleDao(): UserScheduleDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "insync_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}