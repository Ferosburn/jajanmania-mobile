package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerAccountResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: CustomerAccountData? = null,
)

data class CustomerAccountData(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("balance")
    val balance: Long? = null,
    @SerializedName("experience")
    val experience: Long? = null,
    @SerializedName("last_latitude")
    val lastLatitude: Double? = null,
    @SerializedName("last_longitude")
    val lastLongitude: Double? = null,
)
