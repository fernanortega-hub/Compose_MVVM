package com.fernanortega.technical_interview.model.network.client

import com.google.gson.annotations.SerializedName

data class RecallResponse(
    @SerializedName("orderId") val orderId: Int,
    @SerializedName("username") val username: String,
    @SerializedName("subTotal") val subTotal: Double,
    @SerializedName("ticketNumber") val ticketNumber: Int,
    @SerializedName("orderDateTime") val orderDateTime: String,
    @SerializedName("orderType") val orderType: Int
)
