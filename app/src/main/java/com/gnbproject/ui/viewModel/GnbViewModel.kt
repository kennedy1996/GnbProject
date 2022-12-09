package com.gnbproject.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnbproject.entitiy.GnbDtos
import com.gnbproject.repository.GnbRepository
import kotlinx.coroutines.launch

class GnbViewModel : ViewModel() {

    private val repository = GnbRepository()
    private val rates = GnbRates()
    private var transactions = MutableLiveData<List<GnbDtos.Transactions>>()

    fun searchRates() {
        viewModelScope.launch {
            rates.ratesRecibed(repository.getRates())
        }
    }

    fun listRates(): List<String> {
        return rates.listAllRates()
    }
    fun convertCurrency(value: Double, from: String, to: String): Double {
            return rates.tryConvert(value, from, to)
    }
    fun searchTransaction() {
        viewModelScope.launch {
            transactions.value=repository.getTransactions()
        }
    }
    fun getTransaction(): MutableLiveData<List<GnbDtos.Transactions>> {
        return transactions
    }

    fun convertAllTransaction(to: String){
        val oldTransactions = transactions.value!!
        val newTransactions = mutableListOf<GnbDtos.Transactions>()
        oldTransactions.forEach {
            newTransactions.add(GnbDtos.Transactions(
                it.sku,
                convertCurrency(it.amount, it.currency, to),
                to
            ))
        }
        transactions.value = newTransactions
    }
}