package com.tokodizital.jajanmania.common.utils

import java.text.NumberFormat
import java.util.Locale

fun Long.toRupiah(): String {
    val localeID =  Locale.forLanguageTag("in-ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).dropLast(3)
}