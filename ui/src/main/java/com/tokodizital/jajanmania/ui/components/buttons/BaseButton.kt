package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val buttonEnabled = if (isLoading) false else enabled
    val buttonText = if (isLoading) "Loading..." else text
    Button(
        onClick = onClicked,
        modifier = modifier,
        enabled = buttonEnabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Text(
            text = buttonText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun PreviewBaseButton() {
    JajanManiaTheme {
        Surface {
            BaseButton(
                text = "Transfer Bank",
                containerColor = Color(0xFF343434)
            )
        }
    }
}
