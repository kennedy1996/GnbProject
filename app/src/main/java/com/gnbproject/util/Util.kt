package com.gnbproject.util

import android.app.AlertDialog
import android.content.Context
import android.provider.Settings.System.getString
import android.view.View
import android.widget.TextView
import com.gnbproject.R
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
    alertDialog.setTitle(context.getString(R.string.select_currency))
    alertDialog.setSingleChoiceItems(arrayCurrencies, clickedItem) { dialog, which ->
        clickedItem = which
    }
    alertDialog.setPositiveButton(context.getString(R.string.ok)) { _, _ ->
        if (position == null) {
            viewModel.convertAllTransaction(arrayCurrencies[clickedItem]!!)
            currencyNow!!.visibility = View.VISIBLE
            currencyNow!!.text = "${context.getString(R.string.currency_now)} ${arrayCurrencies[clickedItem]!!}"
        } else
            viewModel.convertSingleTransaction(arrayCurrencies[clickedItem]!!, position)
    }
        .setNegativeButton(context.getString(R.string.cancel), null)
        .create()
        .show()
}