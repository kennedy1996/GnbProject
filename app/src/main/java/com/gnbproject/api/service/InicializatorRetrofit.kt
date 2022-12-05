package com.gnbproject.api.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class InicializatorRetrofit {

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(99, TimeUnit.MINUTES)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://android-ios-service.herokuapp.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val gndbApi = retrofit.create(GnbApi::class.java)
}