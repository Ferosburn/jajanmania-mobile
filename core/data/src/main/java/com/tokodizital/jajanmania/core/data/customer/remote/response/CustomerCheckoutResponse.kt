package com.tokodizital.jajanmania.core.data.customer.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerCheckoutResponse(

	@field:SerializedName("data")
	val customerCheckoutData: CustomerCheckoutData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class CustomerCheckoutData(

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("transaction_items")
	val transactionItems: List<TransactionItemsItem?>? = null,

	@field:SerializedName("last_longitude")
	val lastLongitude: Double? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("payment_method")
	val paymentMethod: String? = null,

	@field:SerializedName("last_latitude")
	val lastLatitude: Double? = null
)

data class TransactionItemsItem(

	@field:SerializedName("jajan_item_snapshot_id")
	val jajanItemSnapshotId: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null
)
