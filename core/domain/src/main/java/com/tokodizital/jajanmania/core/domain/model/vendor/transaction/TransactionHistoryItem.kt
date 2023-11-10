package com.tokodizital.jajanmania.core.domain.model.vendor.transaction

data class TransactionHistoryItem(

    val lastLongitude: Double = 0.0,

    val transactionItems: List<TransactionItem> = emptyList(),

    val updatedAt: String = "",

    val userId: String = "",

    val createdAt: String = "",

    val id: String = "",

    val paymentMethod: String = "",

    val lastLatitude: Double = 0.0
)
