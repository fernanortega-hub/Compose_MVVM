package com.fernanortega.technical_interview.ui.edit_order

import androidx.lifecycle.ViewModel
import com.fernanortega.technical_interview.repositories.EditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(repository: EditRepository) : ViewModel() {

}