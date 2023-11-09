package com.tokodizital.jajanmania.core.data.customer

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.NearbyVendorRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.repository.NearbyVendorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NearbyVendorRepositoryImpl(
    private val remoteDataSource: NearbyVendorRemoteDataSource
) : NearbyVendorRepository {

    override suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int
    ): Flow<Resource<List<NearbyVendorResult>>> = flow {
        emit(Resource.Loading)
        when (val nearbyVendorsResponse =
            remoteDataSource.getNearbyVendors(latitude, longitude, pageNumber, pageSize)) {
            is NetworkResponse.Success -> {
                val nearbyVendors = nearbyVendorsResponse.body.toResult()
                emit(Resource.Success(nearbyVendors))
            }

            is NetworkResponse.Error -> {
                val errorMessage =
                    nearbyVendorsResponse.body?.message ?: nearbyVendorsResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}