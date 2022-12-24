package com.fernanortega.technical_interview.model.network.client

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface EditClient {

    @PUT("updateOrder")
    @Headers("API-KEY: TECHNICALTESTabposus.com")
    suspend fun editOrder(@Body body: EditRequest)
}
