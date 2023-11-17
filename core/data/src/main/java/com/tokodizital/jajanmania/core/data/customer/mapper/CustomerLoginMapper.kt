package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult

fun CustomerLoginResponse.toResult(): CustomerLoginResult {
    val loginSession = data?.session
    return CustomerLoginResult(
        accessToken = loginSession?.accessToken ?: "",
        refreshToken = loginSession?.refreshToken ?: "",
        accountType = loginSession?.accountType ?: "",
        accountId = loginSession?.accountId ?: "",
        expiredAt = loginSession?.expiredAt ?: "",
        firebaseToken = loginSession?.firebaseToken ?: ""
    )
}