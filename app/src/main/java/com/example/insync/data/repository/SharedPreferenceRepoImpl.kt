package com.example.insync.data.repository

import com.example.insync.data.local.SharedPreference
import com.example.insync.domain.repository.SharedPreferenceRepository

class SharedPreferenceRepoImpl(private val sharedPreference: SharedPreference) : SharedPreferenceRepository{
    override fun saveString(key: String, value: String) {
        sharedPreference.saveString(key, value)
    }

    override fun getString(key: String): String? {
        return sharedPreference.getString(key)
    }

    override fun saveInt(key: String, value: Int) {
        val strValue = value.toString()
        sharedPreference.saveString(key, strValue)
    }

    override fun getInt(key: String): Int? {
        val strVal = sharedPreference.getString(key);
        return strVal?.toIntOrNull();
    }

    override fun saveBoolean(key: String, value: Boolean) {
        val strVal = value.toString()
        sharedPreference.saveString(key, strVal)
    }

    override fun getBoolean(key: String): Boolean? {
        val strVal = sharedPreference.getString(key)
        return strVal?.toBooleanStrictOrNull()
    }

    override fun saveFloat(key: String, value: Float) {
        val strVal = value.toString()
        sharedPreference.saveString(key, strVal)
    }

    override fun getFloat(key: String): Float? {
        val strVal = sharedPreference.getString(key)
        return strVal?.toFloatOrNull()
    }

    override fun saveLong(key: String, value: Long) {
        val strVal = value.toString()
        sharedPreference.saveString(key, strVal)
    }

    override fun getLong(key: String): Long? {
        val strVal = sharedPreference.getString(key)
        return strVal?.toLongOrNull()
    }

    override fun saveStringSet(key: String, value: Set<String>) {
        sharedPreference.saveString(key, value.toString())
    }

    override fun getStringSet(key: String): Set<String>? {
        val strVal = sharedPreference.getString(key)
        return strVal?.split(",")?.toSet()
    }

    override fun remove(key: String) {
        sharedPreference.saveString(key, "")
    }

    override fun clear() {
        sharedPreference.saveString("", "")
    }


    override fun contains(key: String): Boolean {
        return sharedPreference.getString(key) != null
    }
}