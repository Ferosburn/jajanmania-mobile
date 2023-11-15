package com.tokodizital.jajanmania.customer.profile.manage

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession

data class ManageProfileUiState(
    val fullName: String = "",
    val email: String = "",
    val address: String = "",
    val gender: String = "",
    val errorFullNameMessage: String = "",
    val errorAddressMessage: String = "",
    val customer: Resource<Customer> = Resource.Loading,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshToken: Resource<CustomerRefreshTokenResult> = Resource.Loading
)