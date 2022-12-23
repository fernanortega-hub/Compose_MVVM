package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.network.client.EditClient
import com.fernanortega.technical_interview.model.network.client.EditRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditRepository @Inject constructor(
    private val api: EditClient
) {
    suspend fun editOrder(body: RecallModel) {
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
        }
    }
}
