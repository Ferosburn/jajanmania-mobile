package com.tokodizital.jajanmania.core.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class EWalletMenu(
    @DrawableRes
    val icon: Int,
    @StringRes
    val label: Int
)
