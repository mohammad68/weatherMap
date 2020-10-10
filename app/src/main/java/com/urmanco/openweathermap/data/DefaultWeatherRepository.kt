package com.urmanco.openweathermap.data

import android.util.Log
import com.urmanco.openweathermap.data.error.ERROR_FETCHING_FROM_REMOTE_AND_LOCAL
import com.urmanco.openweathermap.data.error.Error
import com.urmanco.openweathermap.data.local.DefaultWeatherLocalDataSource
import com.urmanco.openweathermap.data.model.Weather
import com.urmanco.openweathermap.data.remote.*
import com.urmanco.openweathermap.data.source.Result

class DefaultWeatherRepository(private val apiService: ApiService,
                               private val localDataSource: DefaultWeatherLocalDataSource) : WeatherRepository {

    override suspend fun getWeather(): Result<Weather> = fetchWeatherFromRemoteOrLocal();

    private suspend fun fetchWeatherFromRemoteOrLocal(): Result<Weather> {

        //------ Remote Fetch
        when(val remoteWeatherResult = fetchWeatherFromRemote()){
            is Result.Success -> {
                refreshLocalDataSource(remoteWeatherResult.data)
                Log.w(TAG,FETCH_FROM_REMOTE)
                remoteWeatherResult.message = FETCH_FROM_REMOTE
                return  remoteWeatherResult
            }
            is Result.DataError -> {
              Log.w(TAG,remoteWeatherResult.error.toString())
            }
        }

        //------Local if remote failed
        val localWeather = localDataSource.getWeather()
        if(localWeather is Result.Success<Weather>){
            Log.w(TAG,FETCH_FROM_CACHE)
            localWeather.message = FETCH_FROM_CACHE
            return localWeather
        } else if(localWeather is Result.DataError){
            Log.w(TAG,localWeather.error.toString())
        }

        //----- Failed Remote and Local
         return  Result.DataError(Error(ERROR_FETCHING_FROM_REMOTE_AND_LOCAL))

    }


    private suspend fun fetchWeatherFromRemote(): Result<Weather> {
        return safeCallApi(){
            apiService.getWeather(LAT,LON,APP_ID,UNITS)
        }
    }

    private suspend fun refreshLocalDataSource(data: Weather) {
        localDataSource.deleteAllWeather()
        localDataSource.saveWeather(data)
    }


    companion object{
        const val TAG = "WeatherRepository"
        const val FETCH_FROM_REMOTE = "Fetch from remote"
        const val FETCH_FROM_CACHE = "fetch from cache"
    }
}
