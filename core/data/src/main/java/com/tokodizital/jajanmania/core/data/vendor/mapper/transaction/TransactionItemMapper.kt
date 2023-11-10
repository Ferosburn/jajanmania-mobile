package com.tokodizital.jajanmania.core.data.vendor.mapper.transaction

import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionResponseItem
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.JajanItem
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionItem

fun TransactionResponseItem.toDomain(): TransactionItem {
    return TransactionItem(
        transactionId = transactionId ?: "",
        jajanItemSnapshotId = jajanItemSnapshotId ?: "",
        quantity = quantity ?: 0,
        jajanItem = jajanItem?.toDomain() ?: JajanItem(),
        updatedAt = updatedAt ?: "",
        createdAt = createdAt ?: "",
        id = id ?: "",
        deletedAt = deletedAt ?: ""
    )
}