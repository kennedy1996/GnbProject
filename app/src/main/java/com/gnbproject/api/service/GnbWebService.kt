package com.gnbproject.api.service

import android.util.Log
import com.gnbproject.entitiy.GnbDtos


private const val TAG = "GnbWebService"

class GnbWebService {
    private val gnbApiService: GnbApi =
        InicializatorRetrofit().gndbApi

    suspend fun getRates(): MutableList<GnbDtos.Rates> {
        var returnV = mutableListOf<GnbDtos.Rates>()
        try {
            val apiReturn = gnbApiService
                .rates()

            apiReturn.forEach {
                returnV.add(GnbDtos.Rates(it.from, it.to, it.rate))
                Log.i(TAG, "${it.from} | ${it.to}")
            }

        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table: ", e)
        }

        return returnV
    }

    suspend fun getTransactions(): MutableList<GnbDtos.Transactions> {
        var returnV = mutableListOf<GnbDtos.Transactions>()
        try {
            val apiReturn = gnbApiService
                .transactions()

            apiReturn.forEach {
                returnV.add(GnbDtos.Transactions(it.sku, it.amount, it.currency))
                Log.i(TAG, "${it.sku} | ${it.amount}| ${it.currency}")
            }

        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table: ", e)
        }

        return returnV
    }

}