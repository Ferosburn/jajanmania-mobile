package com.tokodizital.jajanmania.core.domain.interactor

import androidx.paging.PagingData
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.repository.VendorPagedRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorPagedUseCase
import kotlinx.coroutines.flow.Flow

class VendorPagedInteractor(
    private val vendorPagedRepository: VendorPagedRepository
) : VendorPagedUseCase {

    override fun getTransactionHistory(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<PagingData<TransactionHistoryItem>> {
        return vendorPagedRepository.getTransactionHistory(
            token, page, pageSize, vendorId
        )
    }

    override fun listJajan(
        token: String,
        vendorId: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<PagingData<Jajan>> {
        return vendorPagedRepository.listJajan(
            token, pageNumber, pageSize, vendorId
        )
    }

}