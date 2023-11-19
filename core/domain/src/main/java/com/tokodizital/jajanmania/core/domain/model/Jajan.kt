package com.tokodizital.jajanmania.core.domain.model

data class Jajan(
    val id: String,
    val vendorId: String,
    val name: String,
    val category: String,
    val price: Long,
    val image: String
)
