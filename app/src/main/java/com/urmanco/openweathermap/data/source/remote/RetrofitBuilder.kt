package com.urmanco.openweathermap.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private val okHttpClient = getOkHttpWithInterceptor()

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    private fun getOkHttpWithInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

       return  OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
      }



   val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}