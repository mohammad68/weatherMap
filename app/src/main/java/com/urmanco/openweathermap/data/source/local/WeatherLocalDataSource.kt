package com.urmanco.openweathermap.data.source.local

import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.data.source.WeatherDataSource

class WeatherLocalDataSource: WeatherDataSource {
    override suspend fun getWeather(): Result<Weather> {

        TODO("WeatherDataSource")
    }


}
