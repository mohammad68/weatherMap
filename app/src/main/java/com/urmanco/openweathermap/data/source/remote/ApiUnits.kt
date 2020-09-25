package com.urmanco.openweathermap.data.source.remote

import com.urmanco.openweathermap.data.source.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException



suspend fun <T> safeCallApi(dispacher: CoroutineDispatcher,apiCall: suspend () -> T): Result<T> {

    return  withContext(dispacher){
        try {
            Result.Success(apiCall.invoke())
        }catch (throwable: Throwable){
            when(throwable){
                is IOException -> Result.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorMessage = throwable.message()
                    Result.GenericError(errorMessage)
                }
                else -> {
                    Result.GenericError(null)
                }

            }
        }
    }
}


