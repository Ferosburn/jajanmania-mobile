package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun YellowButton(
    text: String,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.Yellow,
    borderColor: Color = Color.Black,
    roundedRadius: Dp = 8.dp,
    height: Dp = 46.dp,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.height(height),
        shape = RoundedCornerShape(roundedRadius),
        border = BorderStroke(
            width = 2.dp,
            borderColor
        ),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor)
    ) {
        Text(text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}