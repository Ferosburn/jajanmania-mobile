package com.tokodizital.jajanmania.core.domain.model.customer

data class CustomerCheckoutResult(
    val transactionId: String = "",
    val userId: String = "",
    val transactionItems: List<CustomerTransactionItemResult> = listOf(),
    val paymentMethod: String = "",
    val lastLatitude: Double = 0.0,
    val lastLongitude: Double = 0.0,
    val updatedAt: String = "",
    val createdAt: String = "",
)

data class CustomerTransactionItemResult(
    val jajanItemSnapshotId: String = "",
    val quantity: Int = 0
)
