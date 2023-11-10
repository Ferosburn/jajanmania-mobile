package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class VendorResponse(

	@field:SerializedName("data")
	val data: VendorData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class VendorData(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("jajan_image_url")
	val jajanImageUrl: String? = null,

	@field:SerializedName("experience")
	val experience: Double? = null,

	@field:SerializedName("jajan_description")
	val jajanDescription: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("last_longitude")
	val lastLongitude: Double? = null,

	@field:SerializedName("balance")
	val balance: Double? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("jajan_name")
	val jajanName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("last_latitude")
	val lastLatitude: Double? = null
)
