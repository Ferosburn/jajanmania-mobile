package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerLogoutResponse(

    @SerializedName("data")
    val data: Any? = null,

    @SerializedName("message")
    val message: String? = null
)