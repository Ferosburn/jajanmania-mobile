package com.tokodizital.jajanmania.vendor.ewallet

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession


data class EWalletUiState(
    val vendor: Resource<Vendor> = Resource.Loading,
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading,
)
