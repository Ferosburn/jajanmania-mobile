package com.tokodizital.customer.topup

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.model.customer.TopUpResult

data class CustomerTopUpUiState(
    val amount: String = "",
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val customer: Resource<CustomerAccount> = Resource.Loading,
    val refreshToken: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val topUpResult: Resource<TopUpResult> = Resource.Empty,
)
