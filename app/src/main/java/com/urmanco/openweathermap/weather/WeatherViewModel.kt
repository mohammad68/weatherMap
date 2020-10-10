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

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _errorMessageId = MutableLiveData<Int>()
    val errorMessageId: LiveData<Int> = _errorMessageId

    private val _hasError = MutableLiveData(false)
    val hasError: LiveData<Boolean> = _hasError

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    private val _successMessage =  MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage


    init {
        start()
    }

    private fun start() {
        if(_dataLoading.value == true){
            return
        }

        _dataLoading.value = true
        _hasError.value = false
        _errorMessageId.value = R.string.empty_string

        viewModelScope.launch {
            getWeatherUseCase().let { result ->

                _dataLoading.value = false

                if(result is Result.Success){
                    _weather.value = result.data
                    _successMessage.value = result.message
                }else{
                    if(result is Result.DataError){
                        _errorMessageId.value = ErrorMapper.errors[result.error.code]
                        _hasError.value = true
                    }

                }
            }
        }
    }

     fun refresh(){
      start()
    }


}