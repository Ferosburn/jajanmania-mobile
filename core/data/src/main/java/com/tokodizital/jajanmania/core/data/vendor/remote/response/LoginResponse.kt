package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("data")
	val data: RefreshTokenData? = null,

    @SerializedName("message")
	val message: String? = null
)

data class LoginData(

	@SerializedName("session")
	val session: RefreshTokenSession? = null
)

data class LoginSession(

	@SerializedName("access_token")
	val accessToken: String? = null,

	@SerializedName("refresh_token")
	val refreshToken: String? = null,

	@SerializedName("account_type")
	val accountType: String? = null,

	@SerializedName("account_id")
	val accountId: String? = null,

	@SerializedName("expired_at")
	val expiredAt: String? = null
)
