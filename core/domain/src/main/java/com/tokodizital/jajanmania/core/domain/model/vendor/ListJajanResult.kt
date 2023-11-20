package com.tokodizital.jajanmania.core.domain.model.vendor

import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.JajanItem

data class ListJajanResult(
    val listJajan: List<JajanItem> = listOf()
)
