package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @SerializedName("data")
    val data: Any? = null,

    @SerializedName("message")
    val message: String? = null
)