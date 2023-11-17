package com.tokodizital.jajanmania.core.data.vendor.remote.request

import com.google.gson.annotations.SerializedName

data class AddJajanRequest(
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("vendor_id")
    val vendorId: String,
    @SerializedName("name")
    val name: String,
)
