package com.tokodizital.jajanmania.core.data.customer.mapper

import com.tokodizital.jajanmania.core.data.customer.remote.response.subscription.MySubscriptionResponse
import com.tokodizital.jajanmania.core.domain.model.customer.Category

fun MySubscriptionResponse.toDomain() : List<Category> {
    val mySubscriptionCategories = data?.categories?.map {category ->
        Category(
            id = category.id ?: "",
            name = category.name ?: "",
            icon = category.iconUrl ?: "",
            isSubscribed = true
        )
    } ?: listOf()
    return mySubscriptionCategories
}