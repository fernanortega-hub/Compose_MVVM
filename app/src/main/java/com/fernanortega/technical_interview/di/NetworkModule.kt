package com.fernanortega.technical_interview.di

import com.fernanortega.technical_interview.model.network.client.RecallService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://dev-graphql.azurewebsites.net/api/test/"

    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor {
                it.proceed(
                    it.request().newBuilder().addHeader("API-KEY", "TECHNICALTESTabposus.com")
                        .build()
                )
            }.addInterceptor(interceptorLogging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecallService(retrofit: Retrofit): RecallService {
        return retrofit.create(RecallService::class.java)
    }
}