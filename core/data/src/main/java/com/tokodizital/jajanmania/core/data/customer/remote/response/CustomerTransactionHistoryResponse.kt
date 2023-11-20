package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName
import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.JajanItem

data class CustomerTransactionHistoryResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: CustomerTransactionHistoryData? = null,
)

data class CustomerTransactionHistoryData(
    @SerializedName("transaction_histories")
    val transactionHistories: List<CustomerTransactionHistory>? = null,
)

data class CustomerTransactionHistory(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("payment_method")
    val paymentMethod: String? = null,
    @SerializedName("last_latitude")
    val lastLatitude: Double? = null,
    @SerializedName("last_longitude")
    val lastLongitude: Double? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("transaction_items")
    val transactionItems: List<CustomerTransactionItem>? = null,
)

data class CustomerTransactionItem(
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("jajan_item")
    val jajanItems: JajanItem? = null,
)