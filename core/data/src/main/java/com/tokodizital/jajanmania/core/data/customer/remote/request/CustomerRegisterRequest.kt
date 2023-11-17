package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerRegisterRequest(
    @SerializedName("fullName")
    val fullName : String,
    @SerializedName("gender")
    val gender : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("address")
    val address : String = "",
    @SerializedName("lastLatitude")
    val lastLatitude : Double = 0.0,
    @SerializedName("lastLongitude")
    val lastLongitude : Double = 0.0,
)
