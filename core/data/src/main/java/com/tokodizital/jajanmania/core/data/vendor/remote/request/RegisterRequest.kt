package com.tokodizital.jajanmania.core.data.vendor.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String = "",
    @SerializedName("lastLatitude")
    val lastLatitude: Double = 0.0,
    @SerializedName("lastLongitude")
    val lastLongitude: Double = 0.0,
    @SerializedName("jajanImageUrl")
    val jajanImageUrl: String = "",
    @SerializedName("jajanName")
    val jajanName: String = "",
    @SerializedName("jajanDescription")
    val jajanDescription: String = "",
)
