package com.gnbproject.api.service

import com.gnbproject.api.entity.GnbApiDtos
import retrofit2.http.GET

interface GnbApi {

    @GET("transactions")
    suspend fun transactions(): List<GnbApiDtos.Transactions>

    @GET("rates")
    suspend fun rates(): List<GnbApiDtos.Rates>
}