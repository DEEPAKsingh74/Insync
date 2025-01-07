package com.example.insync.data.repository.Schedule

import com.example.insync.data.local.UserScheduleDao
import com.example.insync.data.model.ScheduleEntity
import com.example.insync.data.model.ScheduleStatus
import com.example.insync.domain.repository.CrudChip

class UserSchedule(
    private val userScheduleDao: UserScheduleDao,
): CrudChip {

    override val name: String = "CUSTOM"
    override suspend fun insert(scheduleEntity: ScheduleEntity) {
        userScheduleDao.insert(scheduleEntity);
    }

    override suspend fun getAll(): List<ScheduleEntity> {
        return userScheduleDao.getAll();
    }

    override suspend fun delete(index: Int) {
        userScheduleDao.update(index, ScheduleStatus.CANCELLED.name);
    }
}