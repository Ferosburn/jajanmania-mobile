package com.tokodizital.jajanmania.core.domain.model.customer

data class VendorDetail(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val jajanItems: List<JajanItem> = listOf()
)

data class JajanItem(
    val id: String,
    val name: String,
    val price: Long,
    val imageUrl: String,
    val category: String
)
