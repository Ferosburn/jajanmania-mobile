package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.VendorData
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor

fun VendorData.toDomain(): Vendor {
    return Vendor(
        address = address ?: "",
        gender = gender ?: "",
        createdAt = createdAt ?: "",
        jajanImageUrl = jajanImageUrl ?: "",
        experience = experience ?: 0.0,
        jajanDescription = jajanDescription ?: "",
        fullName = fullName ?: "",
        lastLongitude = lastLongitude ?: 0.0,
        lastLatitude = lastLatitude ?: 0.0,
        balance = balance ?: 0.0,
        updatedAt = updatedAt ?: "",
        jajanName = jajanName ?: "",
        id = id ?: "",
        email = email ?: "",
        username = username ?: "",
        status = status ?: ""
    )
}