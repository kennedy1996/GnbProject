package com.gnbproject.api.service

import android.util.Log
import com.gnbproject.api.entity.GnbApiDtos


private const val TAG = "GnbWebService"

class GnbWebService {
    private val gnbApiService: GnbApi =
        InicializatorRetrofit().gndbApi

    suspend fun getTransactions(): List<GnbApiDtos.Transactions>{
        var returnV =  mutableListOf<GnbApiDtos.Transactions>()
        try {
            val noaGetTableReturn = gnbApiService
                .transactions()

        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table: ", e)
        }
        return returnV
    }

}