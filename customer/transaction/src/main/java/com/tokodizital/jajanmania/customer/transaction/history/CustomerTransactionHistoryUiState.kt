package com.tokodizital.jajanmania.customer.transaction.history

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerTransaction

data class CustomerTransactionHistoryUiState(
    val pageNumber: Int = 1,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val transactionHistory: Resource<List<CustomerTransaction>> = Resource.Empty,
    val transactionHistoryList: List<CustomerTransaction> = listOf(),
)