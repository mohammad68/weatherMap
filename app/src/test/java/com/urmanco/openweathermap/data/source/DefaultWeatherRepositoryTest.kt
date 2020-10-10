package com.urmanco.openweathermap.data.source.local

import com.google.common.truth.Truth
import com.urmanco.openweathermap.data.local.WeatherDataSource
import com.urmanco.openweathermap.data.DefaultWeatherRepository
import com.urmanco.openweathermap.data.source.FakeRemoteDataSource
import com.urmanco.openweathermap.data.source.FakeLocalDataSource
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.data.WeatherRepository
import com.urmanco.openweathermap.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


@ExperimentalCoroutinesApi
class DefaultWeatherRepositoryTest {
    private val weatherDetail = listOf(WeatherDetail(0, 12.5F,12.9F,"clear","2x.png"))
    private val current = Current("40",weatherDetail,1050,100,500000,54.5f)
    private val temp = Temp(12.5f,10.2f)
    private val daily = listOf<Daily>(Daily(temp,weatherDetail))
    private var weather =  Weather(1,current,daily)


    private lateinit var weatherRepository: WeatherRepository
    private lateinit var apiService: FakeRemoteDataSource
    private lateinit var localDataSource: WeatherDataSource


    @Test
    fun getTask_emptyFailedRemoteAndUninitializedCache() = runBlockingTest {

        localDataSource = FakeLocalDataSource()

        //failed remote request
        apiService = FakeRemoteDataSource(null)

        weatherRepository = DefaultWeatherRepository(apiService,localDataSource)
        val result = weatherRepository.getWeather()


        Truth.assertThat(result).isEqualTo(Result.GenericError("Error fetching from remote and local"))
    }


    @ExperimentalCoroutinesApi
    @Test
    fun getTask_firstCatchInit() = runBlockingTest {

        localDataSource = FakeLocalDataSource()
        apiService = FakeRemoteDataSource(weather)
        weatherRepository = DefaultWeatherRepository(apiService,localDataSource)

        val result = weatherRepository.getWeather()

        Truth.assertThat(result).isEqualTo(result)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun getTask_FetchFromCache() = runBlockingTest {

        localDataSource = FakeLocalDataSource()
        apiService = FakeRemoteDataSource(weather)
        weatherRepository = DefaultWeatherRepository(apiService,localDataSource)

        val result = weatherRepository.getWeather()
        Truth.assertThat(result).isEqualTo(result)

        apiService.remoteWeather()

        val weatherCache = weatherRepository.getWeather()
        Truth.assertThat(result).isEqualTo(weatherCache)
    }

}