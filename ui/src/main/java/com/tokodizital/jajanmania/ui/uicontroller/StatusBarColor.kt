package com.tokodizital.jajanmania.ui.uicontroller

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusBarColor(
    color: Color = MaterialTheme.colorScheme.background,
    darkIcons: Boolean = color.luminance() > 0.5f
) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color,
            darkIcons
        )
        onDispose {  }
    }
}