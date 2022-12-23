package com.fernanortega.technical_interview.ui.recall

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import com.fernanortega.technical_interview.repositories.RecallRepository
import javax.inject.Inject

class RecallUseCase @Inject constructor(private val repository: RecallRepository){
    suspend fun invokeNetwork() : List<RecallModel> {
        return repository.getAllFromNetwork()
    }

    suspend fun invokeLocal() : List<RecallModel> {
        return repository.getAllFromLocal()
    }

    suspend fun invokeLocalForId(type: Int) : List<RecallModel> {
        return repository.getForType(type)
    }
}