package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerAccountResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount

fun CustomerAccountResponse.toDomain(): CustomerAccount {
    return CustomerAccount(
        id = data?.id ?: "",
        fullName = data?.fullName ?: "",
        gender = data?.gender ?: "",
        address = data?.address ?: "",
        username = data?.username ?: "",
        email = data?.email ?: "",
        balance = data?.balance ?: 0L,
        experience = data?.experience ?: 0L,
        lastLatitude = data?.lastLatitude ?: 0.0,
        lastLongitude = data?.lastLongitude ?: 0.0,
    )
}
