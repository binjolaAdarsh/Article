package com.app.headlines.network

/**
 * sealed class used as wrapper class for the response to catch error easily if some error occur
 */
sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
    object InProgress : NetworkResult<Nothing>()
}