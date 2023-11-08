package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@get:SerializedName("data")
	val data: RegisterData? = null,

	@get:SerializedName("message")
	val message: String? = null
)

data class RegisterData(

	@get:SerializedName("address")
	val address: String? = null,

	@get:SerializedName("gender")
	val gender: String? = null,

	@get:SerializedName("created_at")
	val createdAt: String? = null,

	@get:SerializedName("jajan_image_url")
	val jajanImageUrl: String? = null,

	@get:SerializedName("experience")
	val experience: Int? = null,

	@get:SerializedName("jajan_description")
	val jajanDescription: String? = null,

	@get:SerializedName("full_name")
	val fullName: String? = null,

	@get:SerializedName("last_longitude")
	val lastLongitude: Int? = null,

	@get:SerializedName("balance")
	val balance: Int? = null,

	@get:SerializedName("updated_at")
	val updatedAt: String? = null,

	@get:SerializedName("jajan_name")
	val jajanName: String? = null,

	@get:SerializedName("id")
	val id: String? = null,

	@get:SerializedName("email")
	val email: String? = null,

	@get:SerializedName("username")
	val username: String? = null,

	@get:SerializedName("status")
	val status: String? = null,

	@get:SerializedName("last_latitude")
	val lastLatitude: Int? = null
)
