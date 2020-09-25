package com.urmanco.openweathermap.data.source.Model

data class Current(val temp: String = "default"
                   ,val weather: List<WeatherDetail> = listOf()
                   ,val pressure: Int = 0
                   ,val humidity: Int = 0
                   ,val visibility: Int = 0
                   ,val wind_speed: Int = 0
)
