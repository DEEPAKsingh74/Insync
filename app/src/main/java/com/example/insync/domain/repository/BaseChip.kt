package com.example.insync.domain.repository

import com.example.insync.data.model.ScheduleEntity

interface BaseChip {

    val name: String

    suspend fun getAll(): List<ScheduleEntity>

    suspend fun delete(index: Int)
}