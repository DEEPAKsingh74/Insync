package com.example.insync.data.repository.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.insync.domain.repository.AlarmManagerInterface
import java.util.Calendar

class AlarmManagerRepo(
    private val context: Context
) : AlarmManagerInterface {

    override fun setAlarm(id: Int, date: String, time: String, description: String, title: String) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        try {
            // Parse the date string (dd/MM/yyyy)
            val dateParts = date.split("/")
            if (dateParts.size != 3) throw IllegalArgumentException("Invalid date format. Expected dd/MM/yyyy.")

            val day = dateParts[0].toInt()
            val month = dateParts[1].toInt() - 1 // Months are 0-based in Calendar
            val year = dateParts[2].toInt()

            // Parse the time string (hh:mm AM/PM)
            val timeParts = time.split(" ")
            if (timeParts.size != 2) throw IllegalArgumentException("Invalid time format. Expected hh:mm AM/PM.")

            val timeComponents = timeParts[0].split(":")
            if (timeComponents.size != 2) throw IllegalArgumentException("Invalid time format. Expected hh:mm.")

            var hour = timeComponents[0].toInt()
            val minute = timeComponents[1].toInt()
            val amPm = timeParts[1].uppercase()

            // Convert 12-hour format to 24-hour format
            if (amPm == "PM" && hour != 12) {
                hour += 12
            } else if (amPm == "AM" && hour == 12) {
                hour = 0
            }

            // Set the calendar
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)


            // Prepare the PendingIntent
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra("scheduleId", id)
                putExtra("title", title)
                putExtra("description", description)
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Set the alarm
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    Log.d("Alarm Manager", "Builde version greater than S and can schedule exact alarms")
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }else{
                    Log.d("Alarm Manager", "Builde version greater than S and can't schedule exact alarms")
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }
            } else {

                Log.d("Alarm Manager", "Builde version less than S")
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }

            Log.d("Alarm Manager", "Alarm set for 10 sec")

        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Error setting alarm: ${e.message}")
        }
    }

    override fun cancelAlarm(id: Int) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        // Creating the PendingIntent with the same ID to cancel the specific alarm
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)  // Cancel the alarm using the PendingIntent
    }
}
