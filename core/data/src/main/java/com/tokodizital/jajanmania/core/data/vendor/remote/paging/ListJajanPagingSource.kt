package com.tokodizital.jajanmania.core.data.vendor.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.response.JajanItemsItem
import com.tokodizital.jajanmania.core.data.vendor.remote.response.ListJajanResponse

class ListJajanPagingSource(
    private val remoteDataSource: VendorJajanManiaRemoteDataSource,
    private val token: String,
    private val vendorId: String
): PagingSource<Int, JajanItemsItem>() {

    override fun getRefreshKey(state: PagingState<Int, JajanItemsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JajanItemsItem> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val listJajanResponse = remoteDataSource.getListJajan(
                token = token,
                page = page,
                pageSize = pageSize,
                vendorId = vendorId
            )
            when (listJajanResponse) {
                is NetworkResponse.Success -> {
                    val prevKey = if (page == 1) null else page.minus(1)
                    val nextKey = if (listJajanResponse.body.data.jajanItems.isEmpty()) null else page.plus(1)
                    val listJajan = listJajanResponse.body.data.jajanItems
                    LoadResult.Page(
                        data = listJajan,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                is NetworkResponse.Error -> {
                    val error = Exception(
                        listJajanResponse.body?.message ?: listJajanResponse.error?.message
                    )
                    LoadResult.Error(error)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}