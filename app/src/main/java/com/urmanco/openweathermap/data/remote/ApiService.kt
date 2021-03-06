package com.urmanco.openweathermap.data.remote

import com.urmanco.openweathermap.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

 @GET("onecall")
 suspend fun getWeather(
       @Query("lat") lat: String
      ,@Query("lon") lon: String
      ,@Query("appid") appId: String
      ,@Query("units") units: String): Weather
}