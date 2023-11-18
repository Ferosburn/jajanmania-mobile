package com.tokodizital.jajanmania.customer.auth.splashscreen

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession

data class SplashScreenUiState(
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val customerRefreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val vendorRefreshTokenResult: Resource<RefreshTokenResult> = Resource.Loading,
)
