package com.tokodizital.jajanmania.core.domain.model.customer

data class CustomerAccount(
    val id: String = "",
    val fullName: String = "",
    val gender: String = "",
    val address: String = "",
    val username: String = "",
    val email: String = "",
    val balance: Long = 0L,
    val experience: Long = 0L,
    val lastLatitude: Double = 0.0,
    val lastLongitude: Double = 0.0,
)
