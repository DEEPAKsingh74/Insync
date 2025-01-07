package com.example.insync.core.di

import android.content.Context
import com.example.insync.core.ChipHandler.ChipHandlerFactory
import com.example.insync.data.local.AppDatabase
import com.example.insync.data.local.SharedPreference
import com.example.insync.data.local.UserScheduleDao
import com.example.insync.data.repository.Alarm.AlarmManagerRepo
import com.example.insync.data.repository.Schedule.UserSchedule
import com.example.insync.presentation.ViewModels.ScheduleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreference {
        return SharedPreference(context);
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserScheduleDao(appDatabase: AppDatabase) =
        appDatabase.userScheduleDao()


    @Provides
    @Singleton
    fun provideUserSchedule(userScheduleDao: UserScheduleDao): UserSchedule {
        return UserSchedule(userScheduleDao)
    }

    @Provides
    @Singleton
    fun provideAlarmManager(@ApplicationContext context: Context): AlarmManagerRepo {
        return AlarmManagerRepo(context)
    }

}