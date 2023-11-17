package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerRefreshTokenResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: CustomerTokenData? = null
)
