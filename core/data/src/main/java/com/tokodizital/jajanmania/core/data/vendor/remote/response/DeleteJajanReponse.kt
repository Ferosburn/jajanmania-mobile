package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteJajanReponse(

	@field:SerializedName("data")
	val data: Any,

	@field:SerializedName("message")
	val message: String
)
