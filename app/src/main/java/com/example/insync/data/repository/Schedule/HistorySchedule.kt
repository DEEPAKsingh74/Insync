package com.example.insync.data.repository.Schedule

import com.example.insync.data.local.UserScheduleDao
import com.example.insync.data.model.ScheduleEntity
import com.example.insync.data.model.ScheduleStatus
import com.example.insync.domain.repository.ReadChip

class HistorySchedule(
    private val userScheduleDao: UserScheduleDao,
    //same for email also
): ReadChip {

    override val name: String = "HISTORY"

    override suspend fun restore(index: Int) {
        userScheduleDao.update(index, ScheduleStatus.PENDING.name);
    }

    override suspend fun getAll(): List<ScheduleEntity> {
        return userScheduleDao.getAllHistory();
    }

    override suspend fun delete(index: Int) {
        userScheduleDao.deletePermanent(index);
    }
}