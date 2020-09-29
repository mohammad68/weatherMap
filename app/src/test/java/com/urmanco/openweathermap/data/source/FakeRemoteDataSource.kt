package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.model.*
import com.urmanco.openweathermap.data.source.remote.ApiService

class FakeRemoteDataSource(val weather: Weather) : ApiService {

    override suspend fun getWeather(lat: String,
        lon: String,
        appId: String,
        units: String
    ) = weather
}