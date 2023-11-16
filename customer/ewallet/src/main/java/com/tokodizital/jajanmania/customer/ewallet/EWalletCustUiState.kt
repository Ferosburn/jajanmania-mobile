package com.tokodizital.jajanmania.customer.ewallet

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession

data class EWalletCustUiState(
    val customer: Resource<CustomerAccount> = Resource.Loading,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val customerRefreshToken: Resource<CustomerRefreshTokenResult> = Resource.Loading
)