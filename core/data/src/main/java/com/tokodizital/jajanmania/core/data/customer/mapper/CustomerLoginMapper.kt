package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.domain.model.customer.LoginResult

fun LoginResponse.toResult(): LoginResult {
    val loginSession = loginData?.loginSession
    return LoginResult(
        accessToken = loginSession?.accessToken ?: "",
        refreshToken = loginSession?.refreshToken ?: "",
        accountType = loginSession?.accountType ?: "",
        accountId = loginSession?.accountId ?: "",
        expiredAt = loginSession?.expiredAt ?: "",
        firebaseToken = loginSession?.firebaseToken ?: ""
    )
}