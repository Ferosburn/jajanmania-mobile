package com.tokodizital.jajanmania.core.data.customer.remote.response.subscription

import com.google.gson.annotations.SerializedName

data class MySubscriptionResponse(
    @SerializedName("data")
    val data: CategoryResponseData? = null,
    @SerializedName("message")
    val message: String? = null,
)
