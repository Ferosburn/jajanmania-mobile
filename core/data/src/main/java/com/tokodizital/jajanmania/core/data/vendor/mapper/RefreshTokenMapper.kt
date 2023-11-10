package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.RefreshTokenResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult

fun RefreshTokenResponse.toResult(): RefreshTokenResult {
    val loginSession = data?.session
    return RefreshTokenResult(
        accessToken = loginSession?.accessToken ?: "",
        refreshToken = loginSession?.refreshToken ?: "",
        accountType = loginSession?.accountType ?: "",
        accountId = loginSession?.accountId ?: "",
        expiredAt = loginSession?.expiredAt ?: ""
    )
}