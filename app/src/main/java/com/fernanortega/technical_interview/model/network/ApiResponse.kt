package com.fernanortega.technical_interview.model.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure<T>(val errorDescription: String, val errorCode: Int) : ApiResponse<T>()
    data class Exception<T>(val exception: kotlin.Exception) : ApiResponse<T>()
}
