package com.example.insync.domain.repository

import com.example.insync.data.model.ScheduleEntity

interface CrudChip: BaseChip {

    //insert
    suspend fun insert(scheduleEntity: ScheduleEntity)

    //update
}