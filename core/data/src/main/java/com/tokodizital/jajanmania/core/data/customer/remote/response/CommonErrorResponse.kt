package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CommonErrorResponse(

    @field:SerializedName("data")
    val data: Any? = null,

    @field:SerializedName("message")
    val message: String? = null
)