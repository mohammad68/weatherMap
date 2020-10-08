package com.urmanco.openweathermap.data.source.local

import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.data.source.WeatherRepository
import com.urmanco.openweathermap.data.source.model.Weather
import kotlin.random.Random

class FakeWeatherRepository :
    WeatherRepository {

    var  weather: Weather = Weather(1)

    var shouldReturnError: Boolean  = false
    fun setReturnError(value: Boolean){
        shouldReturnError = value
    }

    override suspend fun getWeather(): Result<Weather> {
        if(shouldReturnError) return getRandomError()
        return  Result.Success(weather)
    }

    private fun getRandomError(): Result<Weather> {
        val random = Random.nextInt(0,100)
        return if (random <= 0)
            Result.GenericError("Generic Error")
        else
            Result.NetworkError
    }
}