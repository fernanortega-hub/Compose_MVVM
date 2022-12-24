package com.fernanortega.technical_interview.ui.edit_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.technical_interview.model.domain.RecallModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val editUseCase: EditUseCase) : ViewModel() {

    val data = MutableLiveData<RecallModel>()

    fun getOrderData(id: Int) {
        viewModelScope.launch {
            val result = editUseCase.invokeGetDataFromDatabase(id)
            data.value = result
        }
    }

    fun updateOrder(data: RecallModel) {
        viewModelScope.launch {
            editUseCase.invokeUpdateOrder(data)
        }
    }
}