package com.urmanco.openweathermap.weather


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urmanco.openweathermap.data.source.Model.Weather
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.domain.GetWeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(private val getWeatherUseCase : GetWeatherUseCase): ViewModel() {

   private val _dataLoading = MutableLiveData<Boolean>()
   val dataLoading: LiveData<Boolean> = _dataLoading

   private val _error = MutableLiveData<Boolean>()
   val error: LiveData<Boolean> = _error

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather


    init {
        start()
    }

    private fun start() {
        if(_dataLoading.value == true){
            return
        }

        _dataLoading.value = true

        viewModelScope.launch {

            getWeatherUseCase().let { result ->
                if(result is Result.Success){
                    _error.value = false
                    _weather.value = result.data

                }else{
                    _error.value = true
                    _dataLoading.value = false
                }
            }
        }
    }

     fun refresh(){
      start()
    }


}