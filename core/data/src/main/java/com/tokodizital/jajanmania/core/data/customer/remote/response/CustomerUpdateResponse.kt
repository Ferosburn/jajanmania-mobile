package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerUpdateResponse(

	@field:SerializedName("data")
	val customerUpdateData: CustomerUpdateData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class CustomerUpdateData(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("experience")
	val experience: Double? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("last_longitude")
	val lastLongitude: Double? = null,

	@field:SerializedName("balance")
	val balance: Double? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("last_latitude")
	val lastLatitude: Double? = null
)
