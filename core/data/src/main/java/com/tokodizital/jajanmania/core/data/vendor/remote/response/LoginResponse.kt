package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.squareup.moshi.Json

data class LoginResponse(

	@Json(name="data")
	val loginData: LoginData? = null,

	@Json(name="message")
	val message: String? = null
)

data class LoginData(

	@Json(name="session")
	val loginSession: LoginSession? = null
)

data class LoginSession(

	@Json(name="access_token")
	val accessToken: String? = null,

	@Json(name="refresh_token")
	val refreshToken: String? = null,

	@Json(name="account_type")
	val accountType: String? = null,

	@Json(name="account_id")
	val accountId: String? = null,

	@Json(name="expired_at")
	val expiredAt: String? = null
)
