package com.tokodizital.jajanmania.vendor.transaction.history

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem


data class TransactionHistoryUiState(
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading
)
