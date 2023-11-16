package com.tokodizital.jajanmania.vendor.account.profil

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LogoutResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession


data class AccountUiState(
    val vendor: Resource<Vendor> = Resource.Loading,
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading,
    val logoutResult: Resource<LogoutResult> = Resource.Empty
)
