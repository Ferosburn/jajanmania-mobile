package com.tokodizital.jajanmania.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.parseIso8601(): Date? {
    val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return iso8601Format.parse(this)
}

fun Date.toLocalTime() : String {
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return timeFormat.format(this)
}

fun Date.toLocalDate(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(this)
}