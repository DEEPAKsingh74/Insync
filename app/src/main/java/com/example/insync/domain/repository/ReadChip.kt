package com.example.insync.domain.repository

import com.example.insync.data.model.ScheduleEntity

interface ReadChip: BaseChip {

    //restore
    suspend fun restore(index: Int)

    //delete
}