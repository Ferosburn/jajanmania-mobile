package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class JajanItemsResponse(

	@field:SerializedName("data")
	val jajanItemData: JajanItemData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class JajanItemSingleResponse(
	@field:SerializedName("data")
	val jajanItem: JajanItemsItem? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class JajanItemData(

	@field:SerializedName("total_jajan_items")
	val totalJajanItems: Int? = null,

	@field:SerializedName("jajan_items")
	val jajanItems: List<JajanItemsItem?>? = null
)

data class JajanItemsItem(

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("vendor_id")
	val vendorId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null
)
