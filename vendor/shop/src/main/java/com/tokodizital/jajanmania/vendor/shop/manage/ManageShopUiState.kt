package com.tokodizital.jajanmania.vendor.shop.manage

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession

data class ManageShopUiState(
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val isShopActive: Resource<Boolean> = Resource.Loading
)
