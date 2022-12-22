package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.GeneralResponse
import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.domain.toDomain
import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.local.entities.RecallEntity
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import com.fernanortega.technical_interview.model.network.client.RecallService
import retrofit2.HttpException
import javax.inject.Inject

class RecallRepository @Inject constructor(
    private val api: RecallService,
    private val recallDao: RecallDao
) {
    suspend fun getAllFromNetwork(): List<RecallResponse> {
        val response = api.getAll()

        if (response.isNotEmpty()) {
            response.forEach {
                recallDao.insertAll(
                    RecallEntity(
                        it.orderId,
                        it.username,
                        it.subTotal,
                        it.ticketNumber,
                        it.orderDateTime,
                        it.orderType
                    )
                )
            }
        }

        return response
    }

    suspend fun getAllFromLocal(): GeneralResponse<List<RecallModel>> {
        return try {
            val response = recallDao.getAll()
            val finalResponse = response.map {
                it.toDomain()
            }

            GeneralResponse.Success(finalResponse)
        } catch (error: HttpException) {
            GeneralResponse.Failure(error.message(), error.code())
        } catch (error: Exception) {
            GeneralResponse.Exception(error)
        }
    }
}