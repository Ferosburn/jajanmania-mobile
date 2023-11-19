package com.tokodizital.jajanmania.core.domain.usecase

import androidx.paging.PagingData
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import kotlinx.coroutines.flow.Flow

interface VendorPagedUseCase {

    fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String
    ): Flow<PagingData<TransactionHistoryItem>>

    fun listJajan(
        token: String,
        vendorId: String,
        pageNumber: Int = 1,
        pageSize: Int = 10,
    ) : Flow<PagingData<Jajan>>
}