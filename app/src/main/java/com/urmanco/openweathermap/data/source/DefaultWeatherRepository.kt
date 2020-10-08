package com.urmanco.openweathermap.data.source

import android.util.Log
import com.urmanco.openweathermap.data.source.model.Weather
import com.urmanco.openweathermap.data.source.local.WeatherDataSource
import com.urmanco.openweathermap.data.source.remote.*
import java.lang.IllegalStateException

class DefaultWeatherRepository(private val apiService: ApiService,
                               private val localDataSource: WeatherDataSource ) : WeatherRepository {

    private val TAG = "Weather Repository"
    private val ERROR_FETCHING_FROM_REMOTE_AND_LOCAL =  "Error fetching from remote and local"


    override suspend fun getWeather(): Result<Weather> = fetchWeatherFromRemoteOrLocal();



    private suspend fun fetchWeatherFromRemoteOrLocal(): Result<Weather> {

        //Try to fetching remote data
        val remoteWeather = fetchWeatherFromRemote()
        when(remoteWeather){
            is Result.Success ->{
                refreshLocalDataSource(remoteWeather.data)
                return remoteWeather
            }
            is Result.NetworkError -> Log.w(TAG,NETWORK_ERROR)
            is Result.GenericError -> {
                var errorMessage: String = GENERIC_ERROR
                remoteWeather.errorMessage?.let { message -> errorMessage = message }
                Log.w(TAG, errorMessage)
            }
            else -> throw IllegalStateException()
        }

        //Local if remote fails
        val localWeather = localDataSource.getWeather()
        if(localWeather is Result.Success<Weather>) return localWeather


       return Result.GenericError(ERROR_FETCHING_FROM_REMOTE_AND_LOCAL)

    }


    private suspend fun fetchWeatherFromRemote(): Result<Weather>{
        return safeCallApi(){
            apiService.getWeather(LAT,LON,APP_ID,UNITS)
        }
    }

    private suspend fun refreshLocalDataSource(data: Weather) {
        localDataSource.deleteAllWeather()
        localDataSource.saveWeather(data)
    }
}
