package com.gnbproject.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnbproject.repository.GnbRepository
import kotlinx.coroutines.launch

class GnbViewModel : ViewModel() {

    private val repository = GnbRepository()
    private val rates = GnbRates()

    fun getRates() {
        viewModelScope.launch {
            rates.ratesRecibed(repository.getRates())
        }
    }
    fun convertCurrency(value: Double, from: String, to: String) {
        viewModelScope.launch {
            rates.tryConvert(value, from, to)
        }
    }
    fun getTransaction() {
        viewModelScope.launch {
            repository.getTransactions()
        }
    }
}