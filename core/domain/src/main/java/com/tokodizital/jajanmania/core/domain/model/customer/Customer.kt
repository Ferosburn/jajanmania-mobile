package com.tokodizital.jajanmania.core.domain.model.customer

data class Customer(
    val id: String = "",
    val fullName: String = "",
    val gender: String = "",
    val address: String = "",
    val username: String = "",
    val email: String = "",
    val balance: Double = 0.0,
    val experience: Double = 0.0,
    val lastLatitude: Double = 0.0,
    val lastLongitude: Double = 0.0,
    val createdAt: String = "",
    val updatedAt: String = ""

)
