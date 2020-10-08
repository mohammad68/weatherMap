package com.urmanco.openweathermap.domain

import com.urmanco.openweathermap.data.source.model.Weather
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.data.source.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(): Result<Weather> = weatherRepository.getWeather()

}