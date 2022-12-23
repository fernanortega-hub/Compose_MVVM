package com.fernanortega.technical_interview.model.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fernanortega.technical_interview.model.local.entities.RecallEntity
import com.fernanortega.technical_interview.model.network.client.EditRequest
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import com.google.gson.annotations.SerializedName

data class RecallModel(
    val orderId: Int,
    val username: String,
    val subTotal: Double,
    val ticketNumber: Int,
    val orderDateTime: String,
    val orderType: Int
)

fun RecallEntity.toDomain() = RecallModel(orderId, username, subTotal, ticketNumber, orderDateTime, orderType)
fun RecallResponse.toDomain() = RecallModel(orderId, username, subTotal, ticketNumber, orderDateTime, orderType)
//fun EditRequest.toDomain() = RecallModel(orderId, username, subTotal, ticketNumber, orderDateTime, orderType)
