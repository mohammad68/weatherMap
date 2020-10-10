package com.urmanco.openweathermap.data.local.db

import androidx.room.*
import com.urmanco.openweathermap.data.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather")
    suspend fun getWeather(): Weather


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather()

}