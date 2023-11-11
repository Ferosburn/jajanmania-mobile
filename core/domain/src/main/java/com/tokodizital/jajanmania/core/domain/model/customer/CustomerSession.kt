package com.tokodizital.jajanmania.core.domain.model.customer

data class CustomerSession(
    val accountId: String = "",
    val accountType: String = "",
    val accessToken: String = "",
    val refreshToken: String = "",
    val expiredAt: String = "",
    val firebaseToken: String = "",
)
