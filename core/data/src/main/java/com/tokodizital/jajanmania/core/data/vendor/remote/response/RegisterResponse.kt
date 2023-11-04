package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.squareup.moshi.Json

data class RegisterResponse(

	@Json(name="data")
	val data: RegisterData? = null,

	@Json(name="message")
	val message: String? = null
)

data class RegisterData(

	@Json(name="address")
	val address: String? = null,

	@Json(name="gender")
	val gender: String? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="jajan_image_url")
	val jajanImageUrl: String? = null,

	@Json(name="experience")
	val experience: Int? = null,

	@Json(name="jajan_description")
	val jajanDescription: String? = null,

	@Json(name="full_name")
	val fullName: String? = null,

	@Json(name="last_longitude")
	val lastLongitude: Int? = null,

	@Json(name="balance")
	val balance: Int? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="jajan_name")
	val jajanName: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="username")
	val username: String? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="last_latitude")
	val lastLatitude: Int? = null
)
