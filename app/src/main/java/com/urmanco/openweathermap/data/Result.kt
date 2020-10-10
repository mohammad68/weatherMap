package com.urmanco.openweathermap.data.source

import com.urmanco.openweathermap.data.error.Error

sealed class Result<out R> {

    data class Success<out T>(val data: T,var message: String? = ""): Result<T>()
    data class DataError(val error: Error): Result<Nothing>()
    object Loading: Result<Nothing>()


    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[$data]"
            is DataError -> "Error[errorCode=${error.code} description=${error.description}]"
            Loading -> "Loading"
        }
    }
}