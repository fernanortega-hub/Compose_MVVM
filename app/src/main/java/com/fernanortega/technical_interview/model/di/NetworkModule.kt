package com.fernanortega.technical_interview.model.di

import com.fernanortega.technical_interview.model.network.client.EditClient
import com.fernanortega.technical_interview.model.network.client.RecallClient
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
    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://dev-graphql.azurewebsites.net/api/test/")
            .client(OkHttpClient().newBuilder().addInterceptor(interceptorLogging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecallClient(retrofit: Retrofit): RecallClient {
        return retrofit.create(RecallClient::class.java)
    }

    @Singleton
    @Provides
    fun provideEditClient(retrofit: Retrofit): EditClient {
        return retrofit.create(EditClient::class.java)
    }
}