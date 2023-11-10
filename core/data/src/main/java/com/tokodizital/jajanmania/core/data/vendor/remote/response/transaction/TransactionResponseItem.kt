package com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction

import com.google.gson.annotations.SerializedName

data class TransactionResponseItem(

    @field:SerializedName("transaction_id")
    val transactionId: String? = null,

    @field:SerializedName("jajan_item_snapshot_id")
    val jajanItemSnapshotId: String? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("jajan_item")
    val jajanItem: JajanResponseItem? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("deleted_at")
    val deletedAt: String? = null
)
