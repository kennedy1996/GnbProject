package com.gnbproject.api.service

import android.util.Log
import com.gnbproject.api.entity.GnbApiDtos


private const val TAG = "GnbWebService"

class GnbWebService {
    private val gnbApiService: GnbApi =
        InicializatorRetrofit().gndbApi

    suspend fun getTransactions(){
        var returnV =  mutableListOf<GnbApiDtos.Transactions>()
        try {
            val geReturn = gnbApiService
                .rates()

            Log.i(TAG, "getTransactions: ${
                geReturn.size
            }")

            geReturn.forEach {
                Log.i(TAG, "${it.form} | ${it.to}")
            }

        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table: ", e)
        }
    }

}