package com.fernanortega.technical_interview.model.network.client

import retrofit2.http.GET

interface RecallService {
    @GET("getOrders")
    suspend fun getAll() : List<RecallResponse>
}