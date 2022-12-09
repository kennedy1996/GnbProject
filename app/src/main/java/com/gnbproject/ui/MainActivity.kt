package com.gnbproject.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnbproject.databinding.ActivityMainBinding
import com.gnbproject.ui.recyclerView.GnbAdapter
import com.gnbproject.ui.viewModel.GnbViewModel
import com.gnbproject.util.showDialogChangeCurrency

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapterRecyclerView: RecyclerView.Adapter<GnbAdapter.ViewHolder>? = null

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider[GnbViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchData()
        settingsRecyclerView()
        checkingLiveData()
        actionButtonBackInitialData()
        actionButtonUpdateData()
        actionClickChangeAllCurrencies()
    }

    private fun searchData() {
        viewModel.searchRates()
        viewModel.searchTransaction()
    }

    private fun settingsRecyclerView() {
        binding.activityMainRecyclerView.layoutManager = LinearLayoutManager(this)

        adapterRecyclerView = GnbAdapter(
            this,
            viewModel
        )
        binding.activityMainRecyclerView.adapter = adapterRecyclerView
    }

    private fun checkingLiveData() {
        viewModel.getTransaction()?.observe(this, Observer { list ->
            if(list.isNotEmpty()) binding.activityMainProgressBar.visibility= View.GONE
            else binding.activityMainProgressBar.visibility= View.VISIBLE
            adapterRecyclerView!!.notifyDataSetChanged()
        })
    }

    private fun actionButtonBackInitialData() {
        binding.activityMainImageBackCurrency.setOnClickListener {
            viewModel.backInitialTransaction()
            adapterRecyclerView!!.notifyDataSetChanged()
            binding.activityMainTextCurrencyNow.text = "Original Currency"
        }
    }

    private fun actionButtonUpdateData() {
        binding.activityMainImageUpdateData.setOnClickListener {
            searchData()
            adapterRecyclerView!!.notifyDataSetChanged()
            binding.activityMainTextCurrencyNow.text = "Original Currency"
        }
    }

    private fun actionClickChangeAllCurrencies() {
        binding.activityMainTextChangeCurrency.setOnClickListener {
            if(viewModel.getTransaction().value!!.isNotEmpty())
            showDialogChangeCurrency(
                this,
                viewModel,
                currencyNow = binding.activityMainTextCurrencyNow
            )
        }
    }
}