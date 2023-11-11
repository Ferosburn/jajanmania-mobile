package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerLoginResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: CustomerTokenData? = null
)

data class CustomerTokenData(
    @SerializedName("session")
    val session: CustomerTokenSession? = null
)

data class CustomerTokenSession(
    @SerializedName("account_id")
    val accountId: String? = null,
    @SerializedName("account_type")
    val accountType: String? = null,
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("refresh_token")
    val refreshToken: String? = null,
    @SerializedName("expired_at")
    val expiredAt: String? = null,
    @SerializedName("firebase_token")
    val firebaseToken: String? = null
)
