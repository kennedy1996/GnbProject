package com.gnbproject.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnbproject.entitiy.GnbDtos
import com.gnbproject.repository.GnbRepository
import kotlinx.coroutines.launch

class GnbViewModel : ViewModel() {

    private val repository = GnbRepository()
    private val rates = GnbRates()
    private val transactions = MutableLiveData<List<GnbDtos.Transactions>>()
    private val initialTransactions = MutableLiveData<List<GnbDtos.Transactions>>()

    fun searchRates() {
        viewModelScope.launch {
            rates.ratesRecibed(repository.getRates())
        }
    }

    fun listCurrencies(): List<String> {
        return rates.listAllRates()
    }

    fun convertCurrency(value: Double, from: String, to: String): Double {
        return rates.tryConvert(value, from, to)
    }

    fun searchTransaction() {
        viewModelScope.launch {
            transactions.value = repository.getTransactions()
            initialTransactions.value = transactions.value
        }
    }

    fun backInitialTransaction() {
        transactions.value = initialTransactions.value
    }

    fun getTransaction(): MutableLiveData<List<GnbDtos.Transactions>> {
        return transactions
    }

    fun convertAllTransaction(to: String) {
        val oldTransactions = transactions.value!!
        val newTransactions = mutableListOf<GnbDtos.Transactions>()
        oldTransactions.forEach {
            Log.i("convertAllAntes", "convertAllTransaction: ${it.sku}")
            newTransactions.add(
                GnbDtos.Transactions(
                    it.sku,
                    convertCurrency(it.amount, it.currency, to),
                    to
                )
            )
        }
        newTransactions.forEach {
            Log.i("convertAllDepois", "convertAllTransaction: ${it.sku}")
        }
        transactions.value = newTransactions
    }

    fun convertSingleTransaction(to: String, position: Int) {
        val oldTransactions = transactions.value!!
        val newTransactions = mutableListOf<GnbDtos.Transactions>()
        for (i in oldTransactions.indices) {
            if (i == position) {
                newTransactions.add(
                    GnbDtos.Transactions(
                        oldTransactions[i].sku,
                        convertCurrency(oldTransactions[i].amount, oldTransactions[i].currency, to),
                        to
                    )
                )
            } else newTransactions.add(oldTransactions[i])
        }
        transactions.value = newTransactions
    }
}