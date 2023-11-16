package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerUpdateResponse
import com.tokodizital.jajanmania.core.domain.model.customer.Customer

fun CustomerUpdateResponse.toResult() : Customer {
    return Customer(
        id = customerUpdateData?.id ?: "",
        fullName = customerUpdateData?.gender ?: "",
        address = customerUpdateData?.address ?: "",
        username = customerUpdateData?.username ?: "",
        email = customerUpdateData?.email ?: "",
        balance = customerUpdateData?.balance ?: 0.0,
        experience = customerUpdateData?.experience ?: 0.0,
        lastLatitude = customerUpdateData?.lastLatitude ?: 0.0,
        lastLongitude = customerUpdateData?.lastLongitude ?: 0.0,
        createdAt = customerUpdateData?.createdAt ?: "",
        updatedAt = customerUpdateData?.updatedAt ?: ""
    )
}