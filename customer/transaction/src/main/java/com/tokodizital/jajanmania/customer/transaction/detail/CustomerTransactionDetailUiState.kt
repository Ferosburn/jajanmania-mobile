package com.tokodizital.jajanmania.customer.transaction.detail

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerTransaction

data class CustomerTransactionDetailUiState(
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val transactionDetailResult: Resource<CustomerTransaction> = Resource.Loading,
)