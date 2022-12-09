package com.gnbproject.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gnbproject.R
import com.gnbproject.databinding.ActivityMainBinding
import com.gnbproject.ui.viewModel.GnbViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider[GnbViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.searchRates()
        viewModel.searchTransaction()

        Handler(Looper.getMainLooper()).postDelayed({

            viewModel.listRates().forEach {
                Log.i("listRates", "currency: $it")
            }

            viewModel.getTransaction().value!!.forEach {
                Log.i("listRates", "transaction: ${it.amount} ${it.sku} ${it.currency}")
            }
            Log.i("listRates", "tudo para EUR.....")

            viewModel.convertAllTransaction("EUR")

            viewModel.getTransaction().value!!.forEach {
                Log.i("listRates", "transaction: ${it.amount} ${it.sku} ${it.currency}")
            }



        }, 10000)
    }
}