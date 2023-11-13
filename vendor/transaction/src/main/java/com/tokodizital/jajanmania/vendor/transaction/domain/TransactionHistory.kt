package com.tokodizital.jajanmania.vendor.transaction.domain

data class TransactionHistory(
    val transactionId: String = "",
    val price: Long = 0,
    val image: String = "",
    val status: String = "",
    val createdAt: String = "",
    val paymentMethod: String = "",
)
