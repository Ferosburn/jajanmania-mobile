package com.tokodizital.jajanmania.core.domain.model.vendor

data class LoginResult(

    val accessToken: String? = null,

    val refreshToken: String? = null,

    val accountType: String? = null,

    val accountId: String? = null,

    val expiredAt: String? = null
)
