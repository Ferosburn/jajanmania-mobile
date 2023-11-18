package com.tokodizital.jajanmania.core.domain.model.customer

data class CustomerTransaction(
    val transactionId: String = "",
    val vendorName: String = "",
    val vendorImage: String = "",
    val paymentMethod: String = "",
    val totalPrice: Long = 0L,
    val transactionDatetime: String = "",
    val jajanItems: List<JajanItem> = listOf()
)
