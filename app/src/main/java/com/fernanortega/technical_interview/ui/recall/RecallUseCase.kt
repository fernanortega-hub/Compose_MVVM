package com.fernanortega.technical_interview.ui.recall

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import com.fernanortega.technical_interview.repositories.RecallRepository
import javax.inject.Inject

class RecallUseCase @Inject constructor(private val repository: RecallRepository){
    suspend operator fun invoke() : List<RecallResponse> {
        return repository.getAllFromNetwork()
    }
}