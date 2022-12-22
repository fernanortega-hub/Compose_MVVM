package com.fernanortega.technical_interview.ui.recall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.technical_interview.repositories.RecallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecallViewModel @Inject constructor(private val RecallRepository: RecallRepository): ViewModel() {
    fun getAllOrders() {

    }
}