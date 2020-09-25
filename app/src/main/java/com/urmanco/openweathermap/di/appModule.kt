package com.urmanco.openweathermap.di

import com.urmanco.openweathermap.data.source.DefaultWeatherRepository
import com.urmanco.openweathermap.data.source.local.WeatherDatabase
import com.urmanco.openweathermap.data.source.local.DefaultWeatherLocalDataSource
import com.urmanco.openweathermap.data.source.remote.RetrofitBuilder
import com.urmanco.openweathermap.domain.GetWeatherUseCase
import com.urmanco.openweathermap.weather.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { RetrofitBuilder.apiService }
    single { WeatherDatabase.getDatabase(get()).weatherDao()}
    single { DefaultWeatherLocalDataSource(Dispatchers.IO,get()) }
    single { DefaultWeatherRepository(Dispatchers.IO,get(),get()) }
    factory { GetWeatherUseCase(get() ) }
    viewModel { WeatherViewModel(get()) }
}