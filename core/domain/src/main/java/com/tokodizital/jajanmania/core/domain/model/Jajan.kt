package com.tokodizital.jajanmania.core.domain.model

data class Jajan(
    val id: Int,
    val vendorId: Int,
    val name: String,
    val category: String,
    val price: Long,
    val image: String
)
