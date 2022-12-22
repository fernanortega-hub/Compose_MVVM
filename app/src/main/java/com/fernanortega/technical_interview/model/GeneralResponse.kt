package com.fernanortega.technical_interview.model

sealed class GeneralResponse<T> {
    data class Success<T>(val data: T) : GeneralResponse<T>()
    data class Failure<T>(val errorDescription: String, val errorCode: Int) : GeneralResponse<T>()
    data class Exception<T>(val exception: kotlin.Exception) : GeneralResponse<T>()
}
