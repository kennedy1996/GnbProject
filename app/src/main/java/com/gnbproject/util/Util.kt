package com.gnbproject.util

import kotlin.math.roundToInt

     fun arround(amount: Double): Double{
        return (amount * 100).roundToInt().toDouble() / 100
    }