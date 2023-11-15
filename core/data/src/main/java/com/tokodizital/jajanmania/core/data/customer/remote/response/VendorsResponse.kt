package com.tokodizital.jajanmania.core.data.customer.remote.response

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

data class VendorDetail(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("jajan_name")
    val name: String? = null,
    @SerializedName("jajan_description")
    val description: String? = null,
    @SerializedName("jajan_image_url")
    val image: String? = null,
    @SerializedName("jajan_items")
    val jajanItems: List<JajanItem>? = null,
)

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
)

data class Category(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("deleted_at")
    val deletedAt: String? = null,
)
