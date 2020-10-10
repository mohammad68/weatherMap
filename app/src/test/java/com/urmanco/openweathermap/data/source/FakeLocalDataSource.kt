package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.local.WeatherDataSource
import com.urmanco.openweathermap.data.model.Weather
import kotlin.random.Random

class FakeLocalDataSource() : WeatherDataSource {

    private var _weather: Weather? = null

    override suspend fun saveWeather(weather: Weather) {
       _weather = weather
    }

    override suspend fun deleteAllWeather() {
       _weather = null
    }

    override suspend fun getWeather(): Result<Weather>{
        _weather?.let { return Result.Success(it)}
        return  generateError()
    }


    private fun generateError(): Result<Weather> {
        val random = Random.nextInt(0,100)
        return if (random <= 0)
           Result.GenericError("Generic Error")
        else
            Result.NetworkError
    }
}