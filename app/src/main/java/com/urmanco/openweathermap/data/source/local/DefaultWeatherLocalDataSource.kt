package com.urmanco.openweathermap.data.source.local

import com.urmanco.openweathermap.data.source.model.Weather
import com.urmanco.openweathermap.data.source.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception


class DefaultWeatherLocalDataSource(private val weatherDao: WeatherDao): WeatherDataSource {

    override suspend fun getWeather(): Result<Weather> {
        try {
            val weather: Weather? = weatherDao.getWeather()
            weather?.let { return Result.Success(it) }
            return Result.GenericError(DB_IS_EMPTY)

        } catch (e: Exception) {
            return Result.GenericError(GET_QUERY_FAILED)
        }
    }


    override suspend fun saveWeather(weather: Weather) = weatherDao.insertWeather(weather)


    override suspend fun deleteAllWeather() = weatherDao.deleteAllWeather()

}
