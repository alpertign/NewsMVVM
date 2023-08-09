package com.alpertign.newsmvvm.domain.model

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Loading<T>: NetworkResult<T>()

}