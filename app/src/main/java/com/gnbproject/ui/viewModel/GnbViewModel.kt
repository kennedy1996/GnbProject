package com.gnbproject.ui.viewModel

import androidx.lifecycle.ViewModel
import com.gnbproject.repository.GnbRepository

class GnbViewModel : ViewModel() {

    private val repository = GnbRepository()
}