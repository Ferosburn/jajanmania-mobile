package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class TopUpRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("amount")
    val amount: String
)
