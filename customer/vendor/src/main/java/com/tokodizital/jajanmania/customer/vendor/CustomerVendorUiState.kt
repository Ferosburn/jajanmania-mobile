package com.tokodizital.jajanmania.customer.vendor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

data class CustomerVendorUiState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val pageNumber: Int = 1,
    val nearbyVendorsResult: Resource<List<NearbyVendorResult>> = Resource.Empty,
    val nearbyVendors: List<NearbyVendorResult> = listOf()
)
