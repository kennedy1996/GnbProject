package com.gnbproject.api.entity

class GnbApiDtos {

    class Transactions(
        val sku: String,
        val amount: Float,
        val currency: String
    )

    class Rates(
        val form: String,
        val to: String,
        val rate: Double
    )
}