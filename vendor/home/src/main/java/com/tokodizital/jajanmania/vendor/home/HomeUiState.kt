package com.tokodizital.jajanmania.vendor.home

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem


data class HomeUiState(
    val balance: Int = 0,
    val vendor: Resource<Vendor> = Resource.Loading,
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading,
    val transactionHistory: Resource<List<TransactionHistoryItem>> = Resource.Loading
)
