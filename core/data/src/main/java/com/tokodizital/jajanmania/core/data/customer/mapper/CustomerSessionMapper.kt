package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.data.CustomerSession as ProtoCustomerSessionDataStore

fun ProtoCustomerSessionDataStore.toDomain(): CustomerSession {
    return CustomerSession(
        accountId = accountId,
        accountType = accountType,
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiredAt = expiredAt,
        firebaseToken = firebaseToken,
    )
}