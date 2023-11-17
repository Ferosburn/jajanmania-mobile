package com.tokodizital.jajanmania.customer.cart.home

import com.tokodizital.jajanmania.core.data.customer.remote.response.JajanItemsResponse
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail

data class CheckoutUiState(
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,

    val vendorDetailResult: Resource<VendorDetail> = Resource.Empty,
    val vendorJajanItems: Resource<JajanItemsResponse> = Resource.Empty
)