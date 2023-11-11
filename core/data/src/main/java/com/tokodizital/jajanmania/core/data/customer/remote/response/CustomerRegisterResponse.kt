package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerRegisterResponse(
    @get:SerializedName("message")
    val message: String? = null,
    @get:SerializedName("data")
    val data: CustomerRegisterData? = null
)

data class CustomerRegisterData(
    @get:SerializedName("id")
    val id: String? = null,
    @get:SerializedName("full_name")
    val fullName: String? = null,
    @get:SerializedName("gender")
    val gender: String? = null,
    @get:SerializedName("address")
    val address: String? = null,
    @get:SerializedName("username")
    val username: String? = null,
    @get:SerializedName("email")
    val email: String? = null,
    @get:SerializedName("balance")
    val balance: Int? = null,
    @get:SerializedName("experience")
    val experience: Int? = null,
    @get:SerializedName("last_latitude")
    val lastLatitude: Int? = null,
    @get:SerializedName("last_longitude")
    val lastLongitude: Int? = null,
    @get:SerializedName("created_at")
    val createdAt: String? = null,
    @get:SerializedName("updated_at")
    val updatedAt: String? = null,
)
