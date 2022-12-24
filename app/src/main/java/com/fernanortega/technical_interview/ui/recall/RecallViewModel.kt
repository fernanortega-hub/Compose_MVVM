package com.fernanortega.technical_interview.ui.recall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.technical_interview.model.domain.RecallModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecallViewModel @Inject constructor(private val recallUseCase: RecallUseCase) :
    ViewModel() {

    val list = MutableLiveData<List<RecallModel>>()
    val isUiLoading = MutableLiveData(false)

    fun getAllOrdersFromNetwork() {
        viewModelScope.launch {
            isUiLoading.value = true

            val result = recallUseCase.invokeNetwork()
            list.value = result
            isUiLoading.value = false

        }
    }

    fun getAllOrdersFromDatabase() {
        viewModelScope.launch {
            isUiLoading.value = true
            val result = recallUseCase.invokeLocal()
            list.value = result
            isUiLoading.value = false
        }
    }

    fun getForId(type: Int) {
        viewModelScope.launch {
            isUiLoading.value = true
            val result = recallUseCase.invokeLocalForId(type)
            list.value = result
            isUiLoading.value = false
        }
    }
}
