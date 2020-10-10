package com.urmanco.openweathermap.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.urmanco.openweathermap.data.model.Daily

class ConverterDaily {
    private val gson = Gson()
    @TypeConverter
    fun stringToDaily(data: String?): List<Daily> {
        if (data == null) return ArrayList()
        val listType = object : TypeToken<List<Daily?>?>() {}.type
        return gson.fromJson<List<Daily>>(data, listType)
    }

    @TypeConverter
    fun dailyToString(data: List<Daily?>?): String {
        return gson.toJson(data)
    }
}
