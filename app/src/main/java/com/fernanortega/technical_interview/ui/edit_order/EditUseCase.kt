package com.fernanortega.technical_interview.ui.edit_order

import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.repositories.EditRepository
import com.fernanortega.technical_interview.repositories.RecallRepository
import javax.inject.Inject

class EditUseCase @Inject constructor(private val repository: EditRepository) {
    suspend fun invokeUpdateOrder(body: RecallModel) {
        return repository.editOrder(body)
    }
}