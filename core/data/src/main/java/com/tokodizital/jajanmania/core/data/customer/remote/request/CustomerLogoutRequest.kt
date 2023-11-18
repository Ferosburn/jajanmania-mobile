package com.tokodizital.jajanmania.core.data.customer.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerLogoutRequest(
    @SerializedName("session")
    val session: LogoutDataSessionRequest
)

data class LogoutDataSessionRequest(
    @SerializedName("access_token")
    val accessToken: String = "",

    @SerializedName("refresh_token")
    val refreshToken: String = "",

    @SerializedName("account_type")
    val accountType: String = "",

    @SerializedName("account_id")
    val accountId: String = "",

    @SerializedName("expired_at")
    val expiredAt: String = "",

    @SerializedName("firebase_token")
    val firebaseToken: String = ""
)
