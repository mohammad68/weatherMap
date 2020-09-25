package com.urmanco.openweathermap.data.source

sealed class Result<out R> {

    data class Success<out T>(val data: T): Result<T>()
    object NetworkError: Result<Nothing>()
    data class GenericError(val errorMessage: String? = null): Result<Nothing>()
    object Loading: Result<Nothing>()


    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success -> $data"
            is NetworkError -> "Network Error"
            is GenericError -> "Generic Error ->  Message: $errorMessage"
            Loading -> "Loading"
        }
    }
}