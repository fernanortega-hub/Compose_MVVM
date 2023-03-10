package com.fernanortega.technical_interview.model.network.client

import retrofit2.http.GET
import retrofit2.http.Headers

interface RecallClient {
    @GET("getOrders")
    @Headers("API-KEY: TECHNICALTESTabposus.com")
    suspend fun getAll() : List<RecallResponse>
}