package com.tokodizital.jajanmania.core.domain.model.customer

data class LoginResult(
    val accountId: String? = null,
    val accountType: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val expiredAt: String? = null,
    val firebaseToken: String? = null
)