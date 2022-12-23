package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.local.entities.RecallEntity
import com.fernanortega.technical_interview.model.network.client.RecallClient
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import javax.inject.Inject

class RecallRepository @Inject constructor(
    private val api: RecallClient,
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
}