package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class SubscriptionRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("category_id")
    val categoryId: String,
)
