package com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction

import com.google.gson.annotations.SerializedName

data class TransactionHistoryResponse(

	@field:SerializedName("data")
	val data: TransactionHistoryResponseData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class TransactionHistoryResponseData(

	@field:SerializedName("total_transaction_histories")
	val totalTransactionHistories: Int? = null,

	@field:SerializedName("transaction_histories")
	val transactionHistories: List<TransactionHistoryResponseItem>? = null
)



