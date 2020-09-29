package com.urmanco.openweathermap.di

import com.urmanco.openweathermap.data.source.DefaultWeatherRepository
import com.urmanco.openweathermap.data.source.local.WeatherDatabase
import com.urmanco.openweathermap.data.source.local.DefaultWeatherLocalDataSource
import com.urmanco.openweathermap.data.source.remote.RetrofitBuilder
import com.urmanco.openweathermap.domain.GetWeatherUseCase
import com.urmanco.openweathermap.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { RetrofitBuilder.apiService }
    single { WeatherDatabase.getDatabase(get()).weatherDao()}
    single { DefaultWeatherLocalDataSource(get()) }
    single { DefaultWeatherRepository(get(),get()) }
    factory { GetWeatherUseCase(get() ) }
    viewModel { WeatherViewModel(get()) }
}