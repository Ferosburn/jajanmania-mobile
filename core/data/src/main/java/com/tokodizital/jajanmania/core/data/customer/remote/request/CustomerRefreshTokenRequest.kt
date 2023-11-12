package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerRefreshTokenRequest(
    @SerializedName("session")
    val session: CustomerRefreshTokenSessionRequest
)

data class CustomerRefreshTokenSessionRequest(
    @SerializedName("account_id")
    val accountId: String = "",
    @SerializedName("account_type")
    val accountType: String = "",
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("refresh_token")
    val refreshToken: String = "",
    @SerializedName("expired_at")
    val expiredAt: String = "",
    @SerializedName("firebase_token")
    val firebaseToken: String = "",
)
