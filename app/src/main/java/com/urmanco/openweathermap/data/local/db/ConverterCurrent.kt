package com.urmanco.openweathermap.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.urmanco.openweathermap.data.model.Current

class ConverterCurrent {
    private val gson = Gson()
    @TypeConverter
    fun stringToCurrent(data: String?): Current {
        if (data == null) return Current()
        val listType = object : TypeToken<Current>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun currentToString(data: Current): String {
        return gson.toJson(data)
    }
}
