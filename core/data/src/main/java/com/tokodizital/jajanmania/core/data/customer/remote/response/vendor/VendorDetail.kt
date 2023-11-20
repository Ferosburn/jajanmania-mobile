package com.tokodizital.jajanmania.core.data.customer.remote.response.vendor

import com.google.gson.annotations.SerializedName

data class VendorDetail(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("jajan_name")
    val jajanName: String? = null,
    @SerializedName("jajan_description")
    val description: String? = null,
    @SerializedName("jajan_image_url")
    val image: String? = null,
    @SerializedName("jajan_items")
    val jajanItems: List<JajanItem>? = null,
    @SerializedName("status")
    val status: String? = null,
)