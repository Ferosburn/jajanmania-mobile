package com.tokodizital.jajanmania.core.domain.model.vendor

data class RefreshTokenResult(

    val accessToken: String = "",

    val refreshToken: String = "",

    val accountType: String = "",

    val accountId: String = "",

    val expiredAt: String = ""
)
