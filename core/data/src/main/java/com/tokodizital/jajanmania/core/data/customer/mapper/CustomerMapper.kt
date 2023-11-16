package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerResponse
import com.tokodizital.jajanmania.core.domain.model.customer.Customer

fun CustomerResponse.toResult(): Customer {
    val data = customerData
    return Customer(
        id = data?.id ?: "",
        fullName = data?.fullName ?: "",
        gender = data?.gender ?: "",
        address = data?.address ?: "",
        username = data?.username ?: "",
        email = data?.email ?: "",
        balance = data?.balance ?: 0.0,
        experience = data?.experience ?: 0.0,
        lastLatitude = data?.lastLatitude ?: 0.0,
        lastLongitude = data?.lastLongitude ?: 0.0,
        createdAt = data?.createdAt ?: "",
        updatedAt = data?.updatedAt ?: ""
    )
}