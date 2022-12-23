package com.fernanortega.technical_interview.ui.recall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecallViewModel @Inject constructor(private val recallUseCase: RecallUseCase) :
    ViewModel() {

    val list = MutableLiveData<List<RecallResponse>>()
    val isUiLoading = MutableLiveData<Boolean>(false)

    fun getAllOrders() {
        viewModelScope.launch {
            isUiLoading.value = true

            val result = recallUseCase.invoke()
            list.value = result
            isUiLoading.value = false
        }
    }
}
