package com.tokodizital.jajanmania.vendor.home.mapper

import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.vendor.home.domain.TransactionHistory

fun TransactionHistoryItem.toDomain(): TransactionHistory {
    val firstJajan = transactionItems.first().jajanItem
    val totalPrice = transactionItems.sumOf { it.jajanItem.price }.toLong()
    return TransactionHistory(
        transactionId = id,
        price = totalPrice,
        image = firstJajan.imageUrl,
        createdAt = createdAt,
        paymentMethod = paymentMethod
    )
}