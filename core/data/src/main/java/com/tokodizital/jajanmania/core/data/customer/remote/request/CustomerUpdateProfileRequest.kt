package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerUpdateProfileRequest(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("address")
    val address: String
)