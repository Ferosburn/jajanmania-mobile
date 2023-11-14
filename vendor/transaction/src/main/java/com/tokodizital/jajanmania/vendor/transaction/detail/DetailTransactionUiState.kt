package com.tokodizital.jajanmania.vendor.transaction.detail

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem


data class DetailTransactionUiState(
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading,
    val transactionHistory: Resource<TransactionHistoryItem?> = Resource.Loading
)
