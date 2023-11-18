package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class UploadPictureResponse(

	@field:SerializedName("data")
	val data: UrlPicture,

	@field:SerializedName("message")
	val message: String
)

data class UrlPicture(

	@field:SerializedName("url")
	val url: String
)
