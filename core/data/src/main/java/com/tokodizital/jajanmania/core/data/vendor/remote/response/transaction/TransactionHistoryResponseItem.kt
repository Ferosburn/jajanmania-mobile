package com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction

import com.google.gson.annotations.SerializedName

data class TransactionHistoryResponseItem(

    @field:SerializedName("last_longitude")
    val lastLongitude: Double? = null,

    @field:SerializedName("transaction_items")
    val transactionItems: List<TransactionResponseItem>? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("payment_method")
    val paymentMethod: String? = null,

    @field:SerializedName("last_latitude")
    val lastLatitude: Double? = null
)
