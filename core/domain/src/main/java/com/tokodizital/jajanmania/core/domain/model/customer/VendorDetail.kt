package com.tokodizital.jajanmania.core.domain.model.customer

data class VendorDetail(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val jajanItems: List<JajanItem> = listOf(),
    val status: String = ""
)

data class JajanItem(
    val id: String = "",
    val name: String = "",
    val price: Long = 0L,
    val imageUrl: String = "",
    val category: String = "",
    val quantity: Int = 0
)
