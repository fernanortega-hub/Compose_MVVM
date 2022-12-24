package com.fernanortega.technical_interview.model.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.local.entities.RecallEntity

@Dao
interface EditOrderDao {
    @Query("SELECT * FROM order_table WHERE orderId LIKE :id")
    suspend fun getOrder(id: Int) : RecallEntity
}