package com.gnbproject.api.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InicializatorRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(" https://android-ios-service.onrender.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val gndbApi = retrofit.create(GnbApi::class.java)
}