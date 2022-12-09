package com.gnbproject.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.gnbproject.ui.viewModel.GnbViewModel
import kotlin.math.roundToInt

fun arround(amount: Double): Double {
    return (amount * 100).roundToInt().toDouble() / 100
}

fun showDialogChangeCurrency(
    context: Context,
    viewModel: GnbViewModel,
    position: Int? = null,
    currencyNow: TextView? = null
) {
    var alertDialog = AlertDialog.Builder(context)
    var clickedItem = 0
    var listCurrencies = viewModel.listCurrencies()
    var arrayCurrencies: Array<String?> = arrayOfNulls<String>(listCurrencies.size)
    for (i in listCurrencies.indices) {
        arrayCurrencies[i] = listCurrencies[i]
    }
    alertDialog.setTitle("Select Currency")
    alertDialog.setSingleChoiceItems(arrayCurrencies, clickedItem) { dialog, which ->
        clickedItem = which
    }
    alertDialog.setPositiveButton("OK") { _, _ ->
        if (position == null) {
            viewModel.convertAllTransaction(arrayCurrencies[clickedItem]!!)
            currencyNow!!.visibility = View.VISIBLE
            currencyNow!!.text = "Currency now is ${arrayCurrencies[clickedItem]!!}"
        } else
            viewModel.convertSingleTransaction(arrayCurrencies[clickedItem]!!, position)
    }
        .setNegativeButton("Cancel", null)
        .create()
        .show()
}