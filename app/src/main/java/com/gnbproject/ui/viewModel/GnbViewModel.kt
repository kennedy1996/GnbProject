package com.gnbproject.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnbproject.repository.GnbRepository
import kotlinx.coroutines.launch

class GnbViewModel : ViewModel() {

    private val repository = GnbRepository()

    fun getTransaction() {
        viewModelScope.launch {
            repository.getTransactions()
        }
    }
}