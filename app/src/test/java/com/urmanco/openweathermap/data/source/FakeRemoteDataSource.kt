package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.model.*
import com.urmanco.openweathermap.data.source.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalStateException
import kotlin.random.Random

class FakeRemoteDataSource(weather: Weather?) : ApiService {

    private var _weather = weather
    override suspend fun getWeather(lat: String,
        lon: String,
        appId: String,
        units: String): Weather {

        if(_weather == null){
            throw generateApiError()
        }

        return _weather as Weather
    }


    fun remoteWeather(){
        _weather = null
    }


    private fun generateApiError(): Throwable {
        var random = Random.nextInt(0,100)
        if(random in 0..50 ) throw IOException()
        else throw IllegalStateException("illegal State Exception")
    }
}