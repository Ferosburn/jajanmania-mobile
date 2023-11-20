package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class TopUpResponse(
    @SerializedName("data")
    val data: TopUpData? = null,
    @SerializedName("message")
    val message: String? = null
)

data class TopUpData(
    @SerializedName("redirect_url")
    val redirectUrl: String? = null,
)