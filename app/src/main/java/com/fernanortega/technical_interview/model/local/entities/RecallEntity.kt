package com.fernanortega.technical_interview.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "order_table")
data class RecallEntity(
    @PrimaryKey @ColumnInfo(name = "orderId") val orderId: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "subTotal") val subTotal: Double,
    @ColumnInfo(name = "ticketNumber") val ticketNumber: Int,
    @ColumnInfo(name = "orderDateTime") val orderDateTime: String,
    @ColumnInfo(name = "orderType") val orderType: String
)
