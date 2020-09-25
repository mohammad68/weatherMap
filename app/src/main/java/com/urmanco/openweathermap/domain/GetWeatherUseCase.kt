package com.urmanco.openweathermap.domain

import com.urmanco.openweathermap.data.source.DefaultWeatherRepository
import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result

class GetWeatherUseCase(private val weatherRepository: DefaultWeatherRepository) {

    suspend operator fun invoke(): Result<Weather> = weatherRepository.getWeather()

}