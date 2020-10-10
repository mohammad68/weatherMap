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
                return remoteWeatherResult
            }
            is Result.DataError -> {
              Log.w(TAG,remoteWeatherResult.toString())
              return  remoteWeatherResult
            }
        }

        //------Local if remote failed
        val localWeather = localDataSource.getWeather()
        if(localWeather is Result.Success<Weather>)
            return localWeather
        else
            Log.w(TAG,localWeather.toString())


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
    }
}
