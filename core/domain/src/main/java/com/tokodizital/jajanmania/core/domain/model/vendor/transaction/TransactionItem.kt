package com.tokodizital.jajanmania.core.domain.model.vendor.transaction

data class TransactionItem(

    val transactionId: String = "",

    val jajanItemSnapshotId: String = "",

    val quantity: Int = 0,

    val jajanItem: JajanItem = JajanItem(),

    val updatedAt: String = "",

    val createdAt: String = "",

    val id: String = "",

    val deletedAt: String = ""
)
