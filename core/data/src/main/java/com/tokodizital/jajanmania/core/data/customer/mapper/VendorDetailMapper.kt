package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.VendorsResponse
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail

fun VendorsResponse.toDomain(): VendorDetail {
    val vendorDetail = data?.vendors?.firstOrNull()
    return VendorDetail(
        id = vendorDetail?.id ?: "",
        name = if (vendorDetail?.jajanName.isNullOrEmpty())
            "Toko ${vendorDetail?.fullName ?: ""}"
        else vendorDetail?.jajanName ?: "",
        description = vendorDetail?.description ?: "",
        image = vendorDetail?.image ?: "",
        jajanItems = vendorDetail?.jajanItems?.map { jajanItem ->
            JajanItem(
                id = jajanItem.id ?: "",
                name = jajanItem.name ?: "",
                price = jajanItem.price ?: 0L,
                imageUrl = jajanItem.imageUrl ?: "",
                category = jajanItem.category?.name ?: "",
            )
        } ?: listOf(),
        status = vendorDetail?.status ?: ""
    )
}