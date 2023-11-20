package com.tokodizital.jajanmania.core.data.customer.remote.response.vendor

import com.google.gson.annotations.SerializedName

data class JajanItem(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Long? = null,
    @SerializedName("category")
    val category: Category? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("vendor")
    val vendor: VendorDetail? = null,
)