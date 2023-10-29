package com.tokodizital.jajanmania.core.domain.model

data class Vendor(
    val id: String,
    val fullname: String,
    val gender: String,
    val address: String,
    val username: String,
    val email: String,
    val balance: Int,
    val experience: Int,
    val jajanImage: String,
    val jajanName: String,
    val jajanDescription: String,
    val status: String,
    val lastLat: Double,
    val lastLng: Double
)
