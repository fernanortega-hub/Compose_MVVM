package com.fernanortega.technical_interview.repositories

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.domain.toDomain
import com.fernanortega.technical_interview.model.local.dao.RecallDao
import com.fernanortega.technical_interview.model.local.entities.RecallEntity
import com.fernanortega.technical_interview.model.network.client.RecallClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecallRepository @Inject constructor(
    private val api: RecallClient,
    private val recallDao: RecallDao
) {
    suspend fun getAllFromNetwork(): List<RecallModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAll()

            // Al llamar a la API, llenar de datos nuestra base de datos Room
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

            val finalResponse = response.map {
                it.toDomain()
            }

            finalResponse
        }
    }

    suspend fun getAllFromLocal(): List<RecallModel> {
        return withContext(Dispatchers.IO) {
            val response = recallDao.getAll()

            val finalResponse = response.map {
                it.toDomain()
            }

            finalResponse
        }
    }

    suspend fun getForType(type: Int): List<RecallModel> {
        return withContext(Dispatchers.IO) {
            val response = recallDao.getFromId(type)

            val finalResponse = response.map {
                it.toDomain()
            }

            finalResponse
        }
    }
}
