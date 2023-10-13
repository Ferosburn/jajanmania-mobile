package com.tokodizital.jajanmania.core.domain.model

data class TransactionHistory(
    val transactionId: String,
    val vendorId: Int,
    val jajanId: Int,
    val price: Long,
    val image: String,
    val status: String,
    val createdAt: String
)
