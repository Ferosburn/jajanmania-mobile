package com.tokodizital.jajanmania.customer.cart.home

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail

data class CheckoutUiState(
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val account: Resource<CustomerAccount> = Resource.Loading,
    val vendorDetailResult: Resource<VendorDetail> = Resource.Loading,
    val checkoutList: List<JajanItem> = listOf(),
)
