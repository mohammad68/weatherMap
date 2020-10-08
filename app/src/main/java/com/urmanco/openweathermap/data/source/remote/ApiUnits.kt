package com.urmanco.openweathermap.data.source.remote

import com.urmanco.openweathermap.data.source.Result
import retrofit2.HttpException
import java.io.IOException



suspend fun <T> safeCallApi(apiCall: suspend () -> T): Result<T> {
    return try {
            Result.Success(apiCall.invoke())
        }catch (throwable: Throwable){
            when(throwable){
                is IOException -> Result.NetworkError
                is HttpException -> {
                    val errorMessage = throwable.message()
                    Result.GenericError(errorMessage)
                }
                else -> {
                    Result.GenericError(UNKNOWN_EXCEPTION)
                }

            }
        }

}


//پدازش تصویر پدرازش


