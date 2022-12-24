package com.fernanortega.technical_interview.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernanortega.technical_interview.model.local.dao.EditOrderDao
import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.local.entities.RecallEntity

@Database(entities = [RecallEntity::class], version = 1)
abstract class TechnicalInterviewDatabase : RoomDatabase() {
    abstract fun getRecallDao() : RecallDao
    abstract fun getEditOrderDao() : EditOrderDao
}