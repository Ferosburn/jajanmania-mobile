package com.tokodizital.jajanmania.core.data.customer.remote.response.vendor

import com.google.gson.annotations.SerializedName

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