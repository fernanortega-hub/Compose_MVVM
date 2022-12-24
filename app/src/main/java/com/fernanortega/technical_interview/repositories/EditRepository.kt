package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.domain.toDomain
import com.fernanortega.technical_interview.model.local.dao.EditOrderDao
import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.network.client.EditClient
import com.fernanortega.technical_interview.model.network.client.EditRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditRepository @Inject constructor(
    private val api: EditClient,
    private val editOrderDao: EditOrderDao
) {
    suspend fun editOrder(body: RecallModel) : RecallModel {
        return withContext(Dispatchers.IO) {
            val request = api.editOrder(
                EditRequest(
                    body.orderId,
                    body.username,
                    body.subTotal,
                    body.ticketNumber,
                    body.orderDateTime,
                    body.orderType
                )
            )
            val finalResponse = request.toDomain()
            finalResponse
        }
    }

    suspend fun getDataFromDatabase(id: Int) : RecallModel {
        return withContext(Dispatchers.IO) {
            val response = editOrderDao.getOrder(id)

            response.toDomain()
        }
    }
}
