package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

	@SerializedName("data")
	val data: RefreshTokenData? = null,

	@SerializedName("message")
	val message: String? = null
)

data class RefreshTokenData(

	@SerializedName("session")
	val session: RefreshTokenSession? = null
)

data class RefreshTokenSession(

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
