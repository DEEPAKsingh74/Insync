package com.example.insync.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private val sharedPreference: SharedPreferences? =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object{
        private const val PREFERENCE_NAME = "insync"
    }

    fun saveString(key: String, value: String){
        sharedPreference?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String): String? {
        return sharedPreference?.getString(key, null)
    }

}