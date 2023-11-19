package com.tokodizital.jajanmania.core.data.customer.remote.response.vendor

import com.google.gson.annotations.SerializedName

data class VendorsResponse(
    @SerializedName("data")
    val data: VendorsResponseData? = null,
    @SerializedName("message")
    val message: String? = null,
)

data class VendorsResponseData(
    @SerializedName("vendors")
    val vendors: List<VendorDetail>? = null,
)