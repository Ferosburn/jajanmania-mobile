package com.tokodizital.jajanmania.core.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeMenu(
    @DrawableRes
    val icon: Int,
    @StringRes
    val label: Int
)
