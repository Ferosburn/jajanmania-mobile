package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.VendorJajanItemsResponse
import com.tokodizital.jajanmania.core.domain.model.customer.CartJajanItem
import com.tokodizital.jajanmania.core.domain.model.customer.VendorJajanItem

fun VendorJajanItemsResponse.toResult() : VendorJajanItem {
    val data = vendorJajanItemsResponseData?.jajanItems
    val contentList = mutableListOf<CartJajanItem>()
    if (data != null) {
        for (item in data) {
            contentList.add(
                CartJajanItem(
                    item?.id ?: "",
                    item?.vendorId ?: "",
                    item?.categoryId ?: "",
                    item?.name ?: "",
                    item?.price ?: 0,
                    item?.imageUrl?: "",
                    item?.createdAt ?: "",
                    item?.updatedAt ?: "",
                    item?.deletedAt ?: ""
                )
            )
        }
    }
    return VendorJajanItem(contentList.size, contentList)

}