package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.SubscriptionResponse
import com.tokodizital.jajanmania.core.domain.model.customer.SubscriptionResult

fun SubscriptionResponse.toResult(): SubscriptionResult{
    return SubscriptionResult(
        message = message ?: ""
    )
}