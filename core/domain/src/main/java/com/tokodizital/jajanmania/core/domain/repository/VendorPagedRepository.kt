package com.tokodizital.jajanmania.core.domain.repository

import androidx.paging.PagingData
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import kotlinx.coroutines.flow.Flow

interface VendorPagedRepository {

    fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String
    ): Flow<PagingData<TransactionHistoryItem>>

}