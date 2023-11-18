package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.TopUpResponse
import com.tokodizital.jajanmania.core.domain.model.customer.TopUpResult

fun TopUpResponse.toResult(): TopUpResult {
    return TopUpResult(
        redirectUrl = data?.redirectUrl ?: ""
    )
}