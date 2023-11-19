package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class VendorJajanItemsResponse(

	@SerializedName("data")
	val vendorJajanItemsResponseData: VendorJajanItemsResponseData? = null,

	@SerializedName("message")
	val message: String? = null
)

data class VendorJajanItemsSingleResponse(
	@SerializedName("data")
	val jajanItem: JajanItems? = null,

	@SerializedName("message")
	val message: String? = null
)

data class VendorJajanItemsResponseData(

	@SerializedName("total_jajan_items")
	val totalJajanItems: Int? = null,

	@SerializedName("jajan_items")
	val jajanItems: List<JajanItems?>? = null
)

data class JajanItems(

	@SerializedName("category_id")
	val categoryId: String? = null,

	@SerializedName("updated_at")
	val updatedAt: String? = null,

	@SerializedName("price")
	val price: Int? = null,

	@SerializedName("image_url")
	val imageUrl: String? = null,

	@SerializedName("vendor_id")
	val vendorId: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("created_at")
	val createdAt: String? = null,

	@SerializedName("id")
	val id: String? = null,

	@SerializedName("deleted_at")
	val deletedAt: String? = null
)
