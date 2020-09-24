package com.urmanco.openweathermap.data.source.remote

import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.data.source.WeatherDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService: WeatherDataSource{

 @GET("onecall")
 suspend fun getWeather(
       @Query("lat") lat: String
      ,@Query("lat") lon: String
      ,@Query("appid") appId: String
      ,@Query("units") units: String): Result<Weather>
}