package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.network.ApiResponse
import com.fernanortega.technical_interview.model.network.client.RecallService
import retrofit2.HttpException
import javax.inject.Inject

class RecallRepository @Inject constructor(
    private val api: RecallService,
    private val recallDao: RecallDao
) {
    suspend fun GetAllFromNetwork(): ApiResponse<Any> {
        return try {
            val response = api.getAll()

            if (response.isNotEmpty()) {
                response.forEach {

                }
            }

            // TODO: usar Room como cache
            ApiResponse.Success(response)
        } catch (error: HttpException) {
            ApiResponse.Failure(error.message(), error.code())
        } catch (error: Exception) {
            ApiResponse.Exception(error)
        }
    }
}