package com.urmanco.openweathermap.data.error


class Error(val code: Int = DEFAULT_ERROR ,val description: String? = "")

const val SERVER_ERROR = -1
const val NETWORK_ERROR = -2
const val UNKNOWN_REMOTE_ERROR = -3
const val CACHE_ERROR = -4
const val CACHE_IS_EMPTY = -5
const val DEFAULT_ERROR = -6
const val ERROR_FETCHING_FROM_REMOTE_AND_LOCAL = -7

