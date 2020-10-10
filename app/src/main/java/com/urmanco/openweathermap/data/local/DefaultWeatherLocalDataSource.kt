package com.urmanco.openweathermap.data.local

import com.urmanco.openweathermap.data.error.CACHE_ERROR
import com.urmanco.openweathermap.data.error.CACHE_IS_EMPTY
import com.urmanco.openweathermap.data.error.Error
import com.urmanco.openweathermap.data.local.db.WeatherDao
import com.urmanco.openweathermap.data.model.Weather
import com.urmanco.openweathermap.data.source.Result
import java.lang.Exception


class DefaultWeatherLocalDataSource(private val weatherDao: WeatherDao): WeatherDataSource {

    override suspend fun getWeather(): Result<Weather> {
        try {
            val weather: Weather? = weatherDao.getWeather()
            weather?.let { return Result.Success(it) }
            return Result.DataError(Error(CACHE_IS_EMPTY))

        } catch (e: Exception) {
            return Result.DataError(Error(CACHE_ERROR,e.message))
        }
    }


    override suspend fun saveWeather(weather: Weather) = weatherDao.insertWeather(weather)


    override suspend fun deleteAllWeather() = weatherDao.deleteAllWeather()

}
