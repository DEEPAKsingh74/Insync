package com.example.insync.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "schedule")
@TypeConverters(ScheduleConverters::class)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val priority: SchedulePriority = SchedulePriority.MEDIUM,
    val status: ScheduleStatus = ScheduleStatus.PENDING
)

// Enums for status and priority
enum class ScheduleStatus {
    PENDING,
    COMPLETED,
    CANCELLED
}

enum class SchedulePriority {
    HIGH,
    MEDIUM,
    LOW
}

class ScheduleConverters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun toLocalDate(dateString: String): LocalDate = LocalDate.parse(dateString)

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String = time.toString()

    @TypeConverter
    fun toLocalTime(timeString: String): LocalTime = LocalTime.parse(timeString)

    @TypeConverter
    fun fromSchedulePriority(priority: SchedulePriority): String = priority.name

    @TypeConverter
    fun toSchedulePriority(priorityString: String): SchedulePriority =
        SchedulePriority.valueOf(priorityString)

    @TypeConverter
    fun fromScheduleStatus(status: ScheduleStatus): String = status.name

    @TypeConverter
    fun toScheduleStatus(statusString: String): ScheduleStatus =
        ScheduleStatus.valueOf(statusString)
}
