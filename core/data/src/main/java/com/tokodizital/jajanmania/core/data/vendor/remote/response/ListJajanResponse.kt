package com.tokodizital.jajanmania.core.data.vendor.remote.response

import com.google.gson.annotations.SerializedName

data class ListJajanResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class JajanItemsItem(

	@field:SerializedName("category_id")
	val categoryId: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("vendor_id")
	val vendorId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: CategoryItem,

	@field:SerializedName("deleted_at")
	val deletedAt: Any
)

data class Data(

	@field:SerializedName("total_jajan_items")
	val totalJajanItems: Int,

	@field:SerializedName("jajan_items")
	val jajanItems: List<JajanItemsItem>
)

data class CategoryItem(

	@field:SerializedName("icon_url")
	val iconUrl: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any
)
