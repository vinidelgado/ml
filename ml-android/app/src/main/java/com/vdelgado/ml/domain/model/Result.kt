package com.vdelgado.ml.domain.model

sealed class Result<out T : Any> {
    sealed class Success<out T : Any> : Result<T>() {
        data class Content<out T : Any>(val data: T) : Success<T>()
        data class Error(val code: Int, val message: String?, val body: String?) : Success<Nothing>()
    }
    data class Failure(val throwable: Throwable) : Result<Nothing>()
}