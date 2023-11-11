package com.tokodizital.jajanmania.core.data.vendor.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateShopStatusRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("status")
    val status: String
)
