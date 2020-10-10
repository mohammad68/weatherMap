package com.urmanco.openweathermap.data

import com.urmanco.openweathermap.data.model.Weather
import com.urmanco.openweathermap.data.source.Result

interface WeatherRepository {
    suspend fun getWeather(): Result<Weather>
}