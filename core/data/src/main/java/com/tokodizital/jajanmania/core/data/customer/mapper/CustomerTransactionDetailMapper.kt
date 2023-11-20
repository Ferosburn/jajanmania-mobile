package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerTransactionHistoryResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerTransaction
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem

fun CustomerTransactionHistoryResponse.toResult(): CustomerTransaction {
    val transaction = data?.transactionHistories?.firstOrNull()
    return CustomerTransaction(
        transactionId = transaction?.id ?: "",
        vendorName = transaction?.transactionItems?.firstOrNull()?.jajanItems?.vendor?.jajanName ?: "",
        vendorImage = transaction?.transactionItems?.firstOrNull()?.jajanItems?.vendor?.image ?: "",
        paymentMethod = transaction?.paymentMethod ?: "",
        totalPrice = transaction?.transactionItems?.sumOf {
            val quantity = it.quantity ?: 0
            val price = it.jajanItems?.price ?: 0L
            quantity * price
        } ?: 0L,
        transactionDatetime = transaction?.createdAt ?: "",
        jajanItems = transaction?.transactionItems?.map { item ->
            JajanItem(
                id = item.jajanItems?.id ?: "",
                name = item.jajanItems?.name ?: "",
                price = item.jajanItems?.price ?: 0L,
                imageUrl = item.jajanItems?.imageUrl ?: "",
                category = item.jajanItems?.category?.name ?: "",
                quantity = item.quantity ?: 0,
            )
        } ?: listOf()
    )
}