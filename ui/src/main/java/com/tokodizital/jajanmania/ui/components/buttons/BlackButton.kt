package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BlackButton(
    text: String,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Black,
    roundedRadius: Dp = 8.dp,
    height: Dp = 46.dp,
) {
    Button(onClick = {
    },
        modifier = Modifier.height(height),
        shape = RoundedCornerShape(roundedRadius),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    )

    {
        Text(text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
