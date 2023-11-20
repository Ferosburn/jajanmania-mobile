package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.CategoriesResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.Category

fun CategoriesResponse.toDomain() : List<Category> {
    val categories = data?.categories?.map {category ->
        Category(
            id = category.id ?: "",
            name = category.name ?: "",
        )
    } ?: listOf()
    return categories
}