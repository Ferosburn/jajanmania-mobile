package com.tokodizital.jajanmania.customer.cart.home

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CartJajanItem
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import com.tokodizital.jajanmania.core.domain.model.customer.VendorJajanItem

data class CheckoutUiState(
    val vendorId: String = "",
    val customerSession: Resource<CustomerSession> = Resource.Loading,
    val customerAccessToken: String = "",
    val pageNumber: Int = 1,
    val pageSize: Int = 10,
    val vendorJajanItemResource: Resource<VendorJajanItem> = Resource.Empty,
    val listCartJajanItem: List<CartJajanItem> = listOf(),
    val mappedJajan: Map<CartJajanItem, Int> = mapOf(),
    val totalPrice: Int = 0,


    val buttonProceedEnabled: Boolean = false,


    val refreshTokenResult: Resource<CustomerRefreshTokenResult> = Resource.Loading,

    val vendorDetailResult: Resource<VendorDetail> = Resource.Empty
//    val vendorJajanItems: Resource<List<JajanItem>> = Resource.Empty,


)