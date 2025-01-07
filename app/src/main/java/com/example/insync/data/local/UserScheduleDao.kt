package com.example.insync.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.insync.data.model.ScheduleEntity
import com.example.insync.data.model.ScheduleStatus

@Dao
interface UserScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userScheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM schedule WHERE status != :status ORDER BY id DESC")
    suspend fun getAll(status: String = ScheduleStatus.CANCELLED.name): List<ScheduleEntity>

    @Query("SELECT * FROM schedule WHERE status != :status ORDER BY id DESC")
    suspend fun getAllHistory(status: String = ScheduleStatus.PENDING.name): List<ScheduleEntity>

    @Query("UPDATE schedule SET status = :status WHERE id = :index")
    suspend fun update(index: Int, status: String)

    @Query("DELETE FROM schedule WHERE id = :index")
    suspend fun deletePermanent(index: Int)
}