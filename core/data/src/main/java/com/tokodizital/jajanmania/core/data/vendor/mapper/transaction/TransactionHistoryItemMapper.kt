package com.tokodizital.jajanmania.core.data.vendor.mapper.transaction

import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionHistoryResponseItem
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem

fun TransactionHistoryResponseItem.toDomain(): TransactionHistoryItem {
    return TransactionHistoryItem(
        lastLongitude = lastLongitude ?: 0.0,
        lastLatitude = lastLatitude ?: 0.0,
        updatedAt = updatedAt ?: "",
        userId = userId ?: "",
        createdAt = createdAt ?: "",
        id = id ?: "",
        paymentMethod = paymentMethod ?: "",
        transactionItems = transactionItems?.map {
            it.toDomain()
        } ?: emptyList()
    )
}