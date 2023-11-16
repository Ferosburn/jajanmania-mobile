package com.tokodizital.jajanmania.customer.profile.manage

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession

data class ManageProfileUiState(
    val id: String = "",
    val fullName: String = "",
    val email: String = "",
    val address: String = "",
    val gender: String = "",
    val genderCode: String = "",
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordConfirm: String = "",
    val errorFullNameMessage: String = "",
    val errorAddressMessage: String = "",
    val errorOldPasswordMessage: String = "",
    val errorNewPasswordMessage: String = "",
    val errorNewPasswordConfirmMessage: String = "",
    val buttonUpdateEnabled: Boolean = false,
    val customer: Resource<Customer> = Resource.Loading,
    val customerUpdateResult: Resource<Customer> = Resource.Empty,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshToken: Resource<CustomerRefreshTokenResult> = Resource.Loading
)