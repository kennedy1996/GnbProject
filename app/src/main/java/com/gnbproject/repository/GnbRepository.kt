package com.gnbproject.repository

import com.gnbproject.api.service.GnbWebService
import com.gnbproject.entitiy.GnbDtos

class GnbRepository {
    private val webClient = GnbWebService()

    suspend fun getRates(): MutableList<GnbDtos.Rates> {
        return webClient.getRates()
    }

    suspend fun getTransactions(): MutableList<GnbDtos.Transactions> {
        return webClient.getTransactions()
    }


}
