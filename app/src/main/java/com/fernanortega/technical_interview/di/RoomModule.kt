package com.fernanortega.technical_interview.di

import android.content.Context
import androidx.room.Room
import com.fernanortega.technical_interview.model.local.TechnicalInterviewDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TechnicalInterviewDatabase::class.java,
        "tecnical_interview_database").build()

    @Singleton
    @Provides
    fun provideRecallDao(database: TechnicalInterviewDatabase) = database.getRecallDao()
}