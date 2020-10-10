package com.urmanco.openweathermap.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.urmanco.openweathermap.MainCoroutineRule
import com.urmanco.openweathermap.data.source.local.FakeWeatherRepository
import com.urmanco.openweathermap.data.model.Weather
import com.urmanco.openweathermap.domain.GetWeatherUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    private lateinit var weatherViewModel: WeatherViewModel

    private lateinit var weatherRepository: FakeWeatherRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule  = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components
    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()


    @Before
    fun initTest(){
        weatherRepository = FakeWeatherRepository()
    }

    @Test
    fun getWeatherFromViewModelSuccessResult(){
        weatherRepository = FakeWeatherRepository()
        weatherRepository.setReturnError(false)
        weatherViewModel = WeatherViewModel(GetWeatherUseCase(weatherRepository))


        val expectedResult = Weather(1)
        val result  = weatherViewModel.weather.value
        val loading =   weatherViewModel.dataLoading.value
        val error = weatherViewModel.error.value

        assertThat(loading).isTrue()
        assertThat(error).isFalse()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun getWeatherFromViewModelErrorResult(){
        weatherRepository.setReturnError(true)
        weatherViewModel = WeatherViewModel(GetWeatherUseCase(weatherRepository))

        val result  = weatherViewModel.weather.value
        val loading =   weatherViewModel.dataLoading.value
        val error = weatherViewModel.error.value

        assertThat(loading).isFalse()
        assertThat(error).isTrue()
        assertThat(result).isEqualTo(null)
    }
}