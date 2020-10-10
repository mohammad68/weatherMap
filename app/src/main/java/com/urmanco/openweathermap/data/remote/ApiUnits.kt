package com.urmanco.openweathermap.data.remote

import com.urmanco.openweathermap.data.error.Error
import com.urmanco.openweathermap.data.error.NETWORK_ERROR
import com.urmanco.openweathermap.data.error.SERVER_ERROR
import com.urmanco.openweathermap.data.error.UNKNOWN_REMOTE_ERROR
import com.urmanco.openweathermap.data.source.Result
import retrofit2.HttpException
import java.io.IOException


suspend fun <T> safeCallApi(apiCall: suspend () -> T): Result<T> {
    return try {
            Result.Success(apiCall.invoke())
        }catch (throwable: Throwable){
            when(throwable){
                is IOException -> Result.DataError(Error(NETWORK_ERROR,throwable.message))
                is HttpException -> Result.DataError(Error(SERVER_ERROR,throwable.message))
                else -> Result.DataError(Error(UNKNOWN_REMOTE_ERROR,throwable.message))
            }
        }
}
