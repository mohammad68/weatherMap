package com.urmanco.openweathermap.data.source.local

import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception


class DefaultWeatherLocalDataSource(private val ioDispatcher: CoroutineDispatcher, private val weatherDao: WeatherDao): WeatherDataSource {

    override suspend fun getWeather(): Result<Weather>  = withContext(ioDispatcher){
        try{
            val weather: Weather? = weatherDao.getWeather()
            weather?.let { return@withContext Result.Success(it)  }
            return@withContext Result.GenericError(DB_IS_EMPTY)

        }catch(e: Exception){
            return@withContext  Result.GenericError(GET_QUERY_FAILED)
        }

    }

    override suspend fun saveWeather(weather: Weather) = withContext(ioDispatcher){
        weatherDao.insertWeather(weather)
    }

    override suspend fun deleteAllWeather()  = withContext(ioDispatcher){
            weatherDao.deleteAllWeather()
    }
}
