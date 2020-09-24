package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.remote.ApiService
import timber.log.Timber
import java.lang.Exception
import java.lang.IllegalStateException

class DefaultWeatherRepository(private val apiService: ApiService, val localDataSource: WeatherDataSource ): WeatherRepository {
    //?lat=37.55&lon=45.08&appid=e8ec0c1db88e57d94ed46278fb4284b0&units=metric

    private val LAT = "37.55"
    private val LON = "45.08"
    private val APP_ID = "e8ec0c1db88e57d94ed46278fb4284b0"
    private val UNITS = "metric"


    override suspend fun getWeather(): Result<Weather> {
      val weather: Result<Weather> = fetchWeatherFromRemoteOrLocal()
        return weather;

    }

    private suspend fun fetchWeatherFromRemoteOrLocal(): Result<Weather> {

        val remoteWeather = apiService.getWeather(LAT,LON,APP_ID,UNITS)

        when(remoteWeather){
            is Result.Error -> Timber.w("Remote fetch failed")
            is Result.Success -> {
                refreshLocalDataSource(remoteWeather.data)
                return remoteWeather
            }
            else -> throw IllegalStateException()
        }

        // Local if remote fails
        val localWeather = localDataSource.getWeather()
        if(localWeather is Result.Success<Weather>) return localWeather
        return Result.Error(Exception("Error fetching from remote and local"))

    }

    private fun refreshLocalDataSource(data: Weather) {
        TODO("refreshLocalDataSource")
        // Delete all weather
        // add new data
    }


}
