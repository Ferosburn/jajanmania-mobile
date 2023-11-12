package com.tokodizital.jajanmania.customer.vendor.nearbyvendor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

data class CustomerVendorUiState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val pageNumber: Int = 1,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val nearbyVendorsResult: Resource<List<NearbyVendorResult>> = Resource.Empty,
)
