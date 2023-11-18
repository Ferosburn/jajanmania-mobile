package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLogoutResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLogoutResult

fun CustomerLogoutResponse.toResult(): CustomerLogoutResult {
    return CustomerLogoutResult (
        data = data ?: Any(),
        message = message ?: ""
    )
}