package com.urmanco.openweathermap.data.source.local

import androidx.room.*
import com.urmanco.openweathermap.data.source.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather")
    suspend fun getWeather(): Weather


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather()

}