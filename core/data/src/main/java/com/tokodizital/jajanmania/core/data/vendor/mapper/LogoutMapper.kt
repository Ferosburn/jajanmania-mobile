package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.LogoutResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.LogoutResult

fun LogoutResponse.toResult(): LogoutResult {
    return LogoutResult(
        data = data ?: Any(),
        message = message ?: ""
    )
}