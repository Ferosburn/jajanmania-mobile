package com.tokodizital.jajanmania.core.data.customer

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.VendorDetailRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import com.tokodizital.jajanmania.core.domain.repository.VendorDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VendorDetailRepositoryImpl(
    private val remoteDataSource: VendorDetailRemoteDataSource
) : VendorDetailRepository {

    override suspend fun getVendorDetail(vendorId: String): Flow<Resource<VendorDetail>> = flow {
        emit(Resource.Loading)
        when (val vendorDetailResponse = remoteDataSource.getVendorDetail(vendorId)) {
            is NetworkResponse.Success -> {
                val vendorDetail = vendorDetailResponse.body.toResult()
                emit(Resource.Success(vendorDetail))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    vendorDetailResponse.body?.message ?: vendorDetailResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}