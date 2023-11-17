package com.tokodizital.jajanmania.customer.subscription

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession

data class SubscriptionUiState(
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,
    val subscription: Resource<List<Category>> = Resource.Loading,
    val categories: List<Category> = listOf(),
    val pageCount: Int = 1,
)