package com.tokodizital.jajanmania.customer.home

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

data class HomeUiState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isRefresh: Boolean = false,
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val account: Resource<CustomerAccount> = Resource.Loading,
    val nearbyVendorResult: Resource<List<NearbyVendorResult>> = Resource.Loading,
    val mySubscriptionResult: Resource<List<Category>> = Resource.Loading,
    val categoriesResult: Resource<List<Category>> = Resource.Loading,
    val mySubscriptionList: List<Category> = listOf(),
    val categoriesList: List<Category> = listOf()
)