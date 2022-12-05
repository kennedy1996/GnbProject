package com.gnbproject.repository

import com.gnbproject.api.service.GnbWebService

class GnbRepository {
    private val webClient = GnbWebService()

    suspend fun getTransactions(){
        return webClient.getTransactions()
    }


}
