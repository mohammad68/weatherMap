package com.urmanco.openweathermap.data.source.local

import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result

interface WeatherDataSource {
    suspend fun saveWeather(weather: Weather)
    suspend fun deleteAllWeather()
    suspend fun getWeather(): Result<Weather>

}