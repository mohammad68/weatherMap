package com.urmanco.openweathermap.data.model


data class Daily(val temp: Temp = Temp(),val weather: List<WeatherDetail> = listOf())
