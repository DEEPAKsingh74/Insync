package com.example.insync.core.ChipHandler

import com.example.insync.data.local.UserScheduleDao
import com.example.insync.data.repository.Schedule.HistorySchedule
import com.example.insync.data.repository.Schedule.UserSchedule
import com.example.insync.domain.repository.BaseChip

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChipHandlerFactory @Inject constructor(
    private val userScheduleDao: UserScheduleDao
) {

    private val handlers = listOf(
        UserSchedule(userScheduleDao),
        HistorySchedule(userScheduleDao)
    )

    fun getHandlerByName(name: String): BaseChip? {
        return handlers.find { it.name.trim().equals(name.trim(), ignoreCase = true) }
    }

    fun getAllHandlers(): List<String> {
        return handlers.map { it.name }
    }
}
