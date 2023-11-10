package com.tokodizital.jajanmania.core.data.vendor.remote.request

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("session")
    val session: RefreshTokenSessionRequest
)

data class RefreshTokenSessionRequest(
    @SerializedName("access_token")
    val accessToken: String = "",

    @SerializedName("refresh_token")
    val refreshToken: String = "",

    @SerializedName("account_type")
    val accountType: String = "",

    @SerializedName("account_id")
    val accountId: String = "",

    @SerializedName("expired_at")
    val expiredAt: String = ""
)
