package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.local.WeatherLocalDataSource
import com.urmanco.openweathermap.data.source.model.Weather
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeLocalDataSource(var weather: Weather?) : WeatherLocalDataSource{

    override suspend fun saveWeather(weather: Weather) {
       delay(100)
    }

    override suspend fun deleteAllWeather() {
        delay(100)
    }

    override suspend fun getWeather(): Result<Weather>{
        weather?.let { return Result.Success(it)}
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