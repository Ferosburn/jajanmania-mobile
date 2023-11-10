package com.tokodizital.jajanmania.core.data.vendor.mapper.transaction

import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.JajanResponseItem
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.JajanItem

fun JajanResponseItem.toDomain(): JajanItem {
    return JajanItem(
        categoryId = categoryId ?: "",
        updatedAt = updatedAt ?: "",
        price = price ?: 0,
        imageUrl = imageUrl ?: "",
        vendorId = vendorId ?: "",
        name = name ?: "",
        createdAt = createdAt ?: "",
        originId = originId ?: "",
        id = id ?: "",
        deletedAt = deletedAt ?: ""
    )
}