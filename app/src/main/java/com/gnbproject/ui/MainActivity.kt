package com.gnbproject.ui

import android.os.Bundle
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
        setContentView(R.layout.activity_main)
    }
}