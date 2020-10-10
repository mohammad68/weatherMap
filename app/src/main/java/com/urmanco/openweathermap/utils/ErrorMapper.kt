package com.urmanco.openweathermap.utils

import com.urmanco.openweathermap.R
import com.urmanco.openweathermap.data.error.*

object ErrorMapper  {
     val errors: Map<Int, Int>
        get() = mapOf(
            Pair(NETWORK_ERROR,R.string.no_internet),
            Pair(DEFAULT_ERROR,R.string.default_error),
            Pair(CACHE_ERROR,R.string.catch_error),
            Pair(SERVER_ERROR,R.string.server_error),
            Pair(UNKNOWN_REMOTE_ERROR,R.string.server_error),
            Pair(CACHE_IS_EMPTY,R.string.catch_error),
            Pair(DEFAULT_ERROR,R.string.default_error),
            Pair(ERROR_FETCHING_FROM_REMOTE_AND_LOCAL,R.string.catch_error)
        ).withDefault { R.string.default_error}
}