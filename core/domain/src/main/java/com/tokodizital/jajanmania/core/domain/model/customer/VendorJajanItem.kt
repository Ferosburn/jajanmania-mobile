package com.tokodizital.jajanmania.core.domain.model.customer

data class VendorJajanItem(
    val totalJajanItem: Int = 0,
    val jajanItems: List<CartJajanItem> = listOf()
)
