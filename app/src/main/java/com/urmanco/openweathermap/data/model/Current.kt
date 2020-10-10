package com.urmanco.openweathermap.data.model

data class Current(val temp: String = "default"
                   ,val weather: List<WeatherDetail> = listOf()
                   ,val pressure: Int = 0
                   ,val humidity: Int = 0
                   ,val visibility: Int = 0
                   ,val wind_speed: Float = 0f
)
