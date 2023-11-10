package com.tokodizital.jajanmania.customer.vendor

import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem

data class CustomerVendorDetailUiState (
    val vendorId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val jajanItems: List<JajanItem> = listOf()
)