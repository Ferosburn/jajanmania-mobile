package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult

fun RegisterResponse.toResult(): RegisterResult {
    return RegisterResult(
        message = message ?: ""
    )
}