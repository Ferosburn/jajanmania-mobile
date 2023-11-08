package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.data.VendorSession as ProtoVendorSessionDataStore

fun ProtoVendorSessionDataStore.toDomain(): VendorSession {
    return VendorSession(
        accessToken = accessToken,
        refreshToken = refreshToken,
        accountType = accountType,
        accountId = accountId,
        expiredAt = expiredAt
    )
}