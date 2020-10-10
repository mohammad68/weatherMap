package com.urmanco.openweathermap.weather


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urmanco.openweathermap.R
import com.urmanco.openweathermap.data.model.Weather
import com.urmanco.openweathermap.data.source.Result
import com.urmanco.openweathermap.domain.GetWeatherUseCase
import com.urmanco.openweathermap.utils.ErrorMapper
import kotlinx.coroutines.launch

class WeatherViewModel(private val getWeatherUseCase : GetWeatherUseCase): ViewModel() {

   private val _dataLoading = MutableLiveData<Boolean>()
   val dataLoading: LiveData<Boolean> = _dataLoading

   private val _errorMessage = MutableLiveData<Int>()
   val errorMessage: LiveData<Int> = _errorMessage

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
                    _errorMessage.value = R.string.empty_string
                    _weather.value = result.data

                }else{
                    if(result is Result.DataError)
                    _errorMessage.value = ErrorMapper.errors[result.error.code]
                    _dataLoading.value = false
                }
            }
        }
    }

     fun refresh(){
      start()
    }


}