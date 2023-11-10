package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import com.tokodizital.jajanmania.core.domain.repository.VendorDetailRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorDetailUseCase
import kotlinx.coroutines.flow.Flow

class VendorDetailInteractor(
    private val vendorDetailRepository: VendorDetailRepository
) : VendorDetailUseCase {

    override suspend fun getVendorDetail(vendorId: String): Flow<Resource<VendorDetail>> {
        return vendorDetailRepository.getVendorDetail(vendorId)
    }
}