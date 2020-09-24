package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.Model.Weather

interface WeatherDataSource {
    suspend fun getWeather():Result<Weather>
}