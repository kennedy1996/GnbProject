package com.gnbproject.ui.recyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gnbproject.R
import com.gnbproject.ui.viewModel.GnbViewModel
import com.gnbproject.util.showDialogChangeCurrency

class GnbAdapter(
    private val context: Context,
    private val viewModel: GnbViewModel,
): RecyclerView.Adapter<GnbAdapter.ViewHolder>() {

    private var list = viewModel.getTransaction()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sku: TextView? =
            itemView.findViewById(R.id.item_gnb_sku_value)
        val amount: TextView? = itemView.findViewById(R.id.item_gnb_amount_value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context)
            .inflate(R.layout.item_gnb, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.sku!!.text = list.value?.get(position)!!.sku
            val amount = list.value?.get(position)!!.amount.toString()
            val current = list.value?.get(position)!!.currency
            holder.amount!!.text = "$current $amount"

            holder.itemView.setOnClickListener {
                showDialogChangeCurrency(context,viewModel, position= position)
            }
        } catch (e: Exception) {
            Log.e("GnbAdapter", e.message.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.value?.count() ?: 0
    }
}