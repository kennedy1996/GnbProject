package com.gnbproject.api.entity

class GnbApiDtos {

    class Transactions(
        val sku: String,
        val amount: Float,
        val currency: String
    )

    class Rates(
        val from: String,
        val to: String,
        val rate: Double
    )
}