package com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction

import com.google.gson.annotations.SerializedName

data class JajanResponseItem(

    @field:SerializedName("category_id")
    val categoryId: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("vendor_id")
    val vendorId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("origin_id")
    val originId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("deleted_at")
    val deletedAt: String? = null
)
