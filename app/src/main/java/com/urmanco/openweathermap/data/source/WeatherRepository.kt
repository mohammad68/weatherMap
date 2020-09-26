package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.model.Weather

interface WeatherRepository {
    suspend fun getWeather():Result<Weather>
}