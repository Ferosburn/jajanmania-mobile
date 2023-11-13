package com.tokodizital.jajanmania.core.data.vendor.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionHistoryResponseItem

class TransactionHistoryPagingSource(
    private val remoteDataSource: VendorJajanManiaRemoteDataSource,
    private val token: String,
    private val vendorId: String
): PagingSource<Int, TransactionHistoryResponseItem>() {

    override fun getRefreshKey(state: PagingState<Int, TransactionHistoryResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransactionHistoryResponseItem> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val transactionHistoryResponse = remoteDataSource.getTransactionHistory(
                token = token,
                page = page,
                pageSize = pageSize,
                vendorId = vendorId
            )
            when (transactionHistoryResponse) {
                is NetworkResponse.Success -> {
                    val prevKey = if (page == 1) null else page.minus(1)
                    val nextKey = if (transactionHistoryResponse.body.data == null) null else page.plus(1)
                    val transactionHistory = transactionHistoryResponse.body.data?.transactionHistories ?: emptyList()
                    LoadResult.Page(
                        data = transactionHistory,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                is NetworkResponse.Error -> {
                    val error = Exception(
                        transactionHistoryResponse.body?.message ?: transactionHistoryResponse.error?.message
                    )
                    LoadResult.Error(error)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}