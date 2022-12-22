package com.fernanortega.technical_interview.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernanortega.technical_interview.model.local.entities.RecallEntity

@Dao
interface RecallDao {
    @Query("SELECT * FROM order_table")
    suspend fun getAll(): List<RecallEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(order: RecallEntity)
}