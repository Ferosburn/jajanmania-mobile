package com.tokodizital.jajanmania.core.data.customer.remote.response.subscription

import com.google.gson.annotations.SerializedName
import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.Category

data class CategoryResponseData(
    @SerializedName("categories")
    val categories: List<Category>? = null,
)