package com.example.insync.presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insync.core.ChipHandler.ChipHandlerFactory
import com.example.insync.data.model.ScheduleEntity
import com.example.insync.data.repository.Alarm.AlarmManagerRepo
import com.example.insync.domain.repository.CrudChip
import com.example.insync.domain.repository.ReadChip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val chipHandlerFactory: ChipHandlerFactory,
    private val alarmManagerRepo: AlarmManagerRepo
) : ViewModel() {

    private val _scheduleItems = mutableStateOf<List<ScheduleEntity>>(emptyList())
    val scheduleItems: State<List<ScheduleEntity>> = _scheduleItems

    fun loadSchedules(chipName: String) {
        viewModelScope.launch {
            try {
                val chipHandler = chipHandlerFactory.getHandlerByName(chipName)
                if (chipHandler == null) {
                    Log.e("ScheduleViewModel", "No handler found for chip: $chipName")
                    return@launch
                }

                val schedules = chipHandler.getAll();
                Log.d("ScheduleViewModel", "Fetched schedules: $schedules")

                _scheduleItems.value = schedules
            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error loading schedules for chip: $chipName", e)
            }
        }
    }

    fun insertSchedule(scheduleEntity: ScheduleEntity, chipName: String) {
        viewModelScope.launch {
            try {
                val chipHandler = chipHandlerFactory.getHandlerByName(chipName)
                if (chipHandler == null || chipHandler !is CrudChip) {
                    Log.e("ScheduleViewModel", "No handler found for chip: $chipName")
                    return@launch
                }

                chipHandler.insert(scheduleEntity)
                loadSchedules(chipName)

                alarmManagerRepo.setAlarm(
                    id = scheduleEntity.id,
                    date = scheduleEntity.date,
                    time = scheduleEntity.time,
                    title = scheduleEntity.name,
                    description = scheduleEntity.description
                )

                Log.d("ScheduleViewModel", "Inserted schedule: $scheduleEntity for chip: $chipName")
            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error inserting schedule for chip: $chipName", e)
            }
        }
    }


    fun deleteSchedule(index: Int, chipName: String) {
        viewModelScope.launch {
            try {
                val chipHandler = chipHandlerFactory.getHandlerByName(chipName)
                if (chipHandler == null) {
                    Log.e("ScheduleViewModel", "No handler found for chip: $chipName")
                    return@launch
                }

                chipHandler.delete(index)
                loadSchedules(chipName)

                alarmManagerRepo.cancelAlarm(index)
                Log.d("ScheduleViewModel", "Deleted schedule at index: $index for chip: $chipName")
            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error deleting schedule at index: $index for chip: $chipName", e)
            }
        }
    }

    fun restoreSchedule(index: Int, chipName: String){
        viewModelScope.launch {
            try{

                val chipHandler = chipHandlerFactory.getHandlerByName(chipName)
                if (chipHandler == null || chipHandler !is ReadChip) {
                    Log.e("ScheduleViewModel", "No handler found for chip: $chipName")
                    return@launch
                }

                chipHandler.restore(index)
                loadSchedules(chipName)


                Log.d("ScheduleViewModel", "Restore schedule at index: $index for chip: $chipName")

            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error deleting schedule at index: $index for chip: $chipName", e)
            }
        }
    }
}
