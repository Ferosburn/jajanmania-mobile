package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

fun NearbyVendorsResponse.toResult(): List<NearbyVendorResult> {
    val nearbyVendorResult = data?.nearbyVendors?.map { nearbyVendor ->
        NearbyVendorResult(
            id = nearbyVendor.vendor?.id ?: "",
            name = if (nearbyVendor.vendor?.jajanName.isNullOrEmpty())
                "Toko ${nearbyVendor.vendor?.fullName ?: ""}"
            else nearbyVendor.vendor?.jajanName ?: "",
            description = nearbyVendor.vendor?.description ?: "",
            image = nearbyVendor.vendor?.image ?: "",
            status = nearbyVendor.vendor?.status ?: "",
        )
    } ?: listOf()
    return nearbyVendorResult
}