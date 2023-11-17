package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRefreshTokenResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult

fun CustomerRefreshTokenResponse.toResult(): CustomerRefreshTokenResult {
    val loginSession = data?.session
    return CustomerRefreshTokenResult(
        accessToken = loginSession?.accessToken ?: "",
        refreshToken = loginSession?.refreshToken ?: "",
        accountType = loginSession?.accountType ?: "",
        accountId = loginSession?.accountId ?: "",
        expiredAt = loginSession?.expiredAt ?: "",
        firebaseToken = loginSession?.firebaseToken ?: ""
    )
}