package com.tokodizital.jajanmania.core.data.vendor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tokodizital.jajanmania.core.data.vendor.mapper.toDomain
import com.tokodizital.jajanmania.core.data.vendor.mapper.transaction.toDomain
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.paging.ListJajanPagingSource
import com.tokodizital.jajanmania.core.data.vendor.remote.paging.TransactionHistoryPagingSource
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.repository.VendorPagedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VendorPagedRepositoryImpl(
    private val remoteDataSource: VendorJajanManiaRemoteDataSource
) : VendorPagedRepository {

    override fun getTransactionHistory(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<PagingData<TransactionHistoryItem>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {
                TransactionHistoryPagingSource(remoteDataSource, token, vendorId)
            }
        ).flow.map {
            it.map { transactionItem ->
                transactionItem.toDomain()
            }
        }
    }

    override fun listJajan(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<PagingData<Jajan>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {
                ListJajanPagingSource(remoteDataSource, token, vendorId)
            }
        ).flow.map {
            it.map { jajanItem ->
                jajanItem.toDomain()
            }
        }
    }
}