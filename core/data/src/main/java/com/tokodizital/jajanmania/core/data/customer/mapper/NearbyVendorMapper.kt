package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

fun NearbyVendorsResponse.toResult(): List<NearbyVendorResult> {
    val nearbyVendorResult = data?.nearbyVendors?.map {nearbyVendor ->  
        NearbyVendorResult(
            id = nearbyVendor.vendor?.id ?: "",
            name = nearbyVendor.vendor?.name ?: "",
            description = nearbyVendor.vendor?.description ?: "",
            image = nearbyVendor.vendor?.image ?: ""
        )
    } ?: listOf()
    return nearbyVendorResult
}