package com.example.insync.domain.repository

interface AlarmManagerInterface {

    fun setAlarm(id: Int, date: String, time: String, description: String, title: String)

    fun cancelAlarm(id: Int)
}