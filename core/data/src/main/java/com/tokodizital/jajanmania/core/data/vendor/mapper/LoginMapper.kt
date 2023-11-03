package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult

fun LoginResponse.toResult(): LoginResult {
    val loginSession = loginData?.loginSession
    return LoginResult(
        accessToken = loginSession?.accessToken ?: "",
        refreshToken = loginSession?.refreshToken ?: "",
        accountType = loginSession?.accountType ?: "",
        accountId = loginSession?.accountId ?: "",
        expiredAt = loginSession?.expiredAt ?: ""
    )
}