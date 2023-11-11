package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRegisterResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult

fun CustomerRegisterResponse.toResult(): CustomerRegisterResult {
    return CustomerRegisterResult(
        message = message ?: ""
    )
}