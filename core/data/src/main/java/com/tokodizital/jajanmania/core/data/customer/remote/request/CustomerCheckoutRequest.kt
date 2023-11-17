package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerCheckoutRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("jajan_item_id")
    val JajanItemId: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("last_latitude")
    val lastLatitude: Double,
    @SerializedName("last_longitude")
    val lastLongitude: Double,
)
