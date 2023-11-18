package com.tokodizital.jajanmania.customer.profile

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLogoutResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession



data class ProfileUiState(
    val customer: Resource<Customer> = Resource.Loading,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val customerRefreshToken: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val customerLogoutResult: Resource<CustomerLogoutResult> = Resource.Empty
)