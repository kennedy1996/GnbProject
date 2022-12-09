package com.gnbproject.ui.viewModel

import com.gnbproject.entitiy.GnbDtos

class GnbRates {

    var ratesNew = mutableListOf<GnbDtos.Rates>()

    fun inicializate() {
        rateInverter()
        listAllRates()
    }

    fun ratesRecibed(rates: MutableList<GnbDtos.Rates>) {
        ratesNew.addAll(rates)
        inicializate()
    }

    fun rateInverter() {
        var listInverter = mutableListOf<GnbDtos.Rates>()
        ratesNew.forEach {
            if (!rateInverterExists(it.from, it.to)) {
                listInverter.add(
                    GnbDtos.Rates(
                        it.to,
                        it.from,
                        1 / it.rate
                    )
                )
            }
        }
        ratesNew.addAll(listInverter)
    }

    fun converterDirect(value: Double, from: String, to: String): Double {
        val filter = ratesNew.filter { it.from == from && it.to == to }[0].rate
        return filter * value
    }


    fun rateInverterExists(from: String, to: String): Boolean {
        val filter = ratesNew.filter { it.from == to && it.to == from }
        return filter.isNotEmpty()
    }

    fun listAllRates(): MutableList<String> {
        var listAllRates = mutableListOf<String>()
        ratesNew.forEach {
            if (!listAllRates.contains(it.to)) listAllRates.add(it.to)
            if (!listAllRates.contains(it.from)) listAllRates.add(it.from)
        }
        return listAllRates
    }

    fun tryConvert(value: Double, from: String, to: String): Double {
        var returnV = 0.0
        val listConvert = convert(value, from, to)
        returnV = if (listConvert[0].to == to) {
            convert(value, from, to)[0].rate
        } else {
            val listConvertNew = convert(listConvert[0].rate, listConvert[0].to, to)
            listConvertNew[0].rate
        }
        return returnV
    }

    fun convert(value: Double, from: String, to: String): List<GnbDtos.Rates> {
        var returnV = mutableListOf<GnbDtos.Rates>()
        val filterDirect = ratesNew.any { it.from == from && it.to == to }
        if (filterDirect) {
            returnV.add(GnbDtos.Rates(from, to, converterDirect(value, from, to)))
        } else {
            val filterFrom = ratesNew.filter { it.from == from }
            val filterFromStartTo = ratesNew.filter { it.from == filterFrom[0].to && it.to == to }
            if (filterFromStartTo.isNotEmpty()) {
                val firstChange = converterDirect(value, filterFrom[0].from, filterFrom[0].to)
                val secondChange = converterDirect(firstChange, filterFrom[0].to, to)
                returnV.add(GnbDtos.Rates(filterFrom[0].to, to, secondChange))
            } else {
                val firstChange = converterDirect(value, filterFrom[0].from, filterFrom[0].to)
                val newFilter = ratesNew.filter { it.from == filterFrom[0].to && it.to == to }
                if (newFilter.isNotEmpty()) {
                    returnV.add(
                        GnbDtos.Rates(
                            newFilter[0].from,
                            to,
                            converterDirect(firstChange, newFilter[0].from, to)
                        )
                    )
                } else {
                    returnV.add(GnbDtos.Rates(filterFrom[0].from, filterFrom[0].to, firstChange))
                }
            }
        }
        return returnV
    }
}
