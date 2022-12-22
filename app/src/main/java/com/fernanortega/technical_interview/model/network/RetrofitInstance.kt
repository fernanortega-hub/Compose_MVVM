package com.fernanortega.technical_interview.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://dev-graphql.azurewebsites.net/api/test/"

private val interceptorLogging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

@Singleton
object RetrofitInstance {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor(interceptorLogging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}