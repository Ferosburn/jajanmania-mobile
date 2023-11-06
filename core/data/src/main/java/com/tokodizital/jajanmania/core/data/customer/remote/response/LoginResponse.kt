package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "message") val message: String? = null,
    @Json(name = "data") val loginData: LoginData? = null
)

data class LoginData(
    @Json(name = "session") val loginSession: LoginSession? = null
)

data class LoginSession(
    @Json(name = "account_id") val accountId: String? = null,
    @Json(name = "account_type") val accountType: String? = null,
    @Json(name = "access_token") val accessToken: String? = null,
    @Json(name = "refresh_token") val refreshToken: String? = null,
    @Json(name = "expired_at") val expiredAt: String? = null,
    @Json(name = "firebase_token") val firebaseToken: String? = null
)
