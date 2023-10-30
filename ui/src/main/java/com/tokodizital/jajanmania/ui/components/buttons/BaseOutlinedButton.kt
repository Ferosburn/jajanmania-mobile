package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun BaseOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit = {},
    borderColor: Color = MaterialTheme.colorScheme.outline,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClicked,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor
        ),
        contentPadding = contentPadding,
        border = BorderStroke(
            width = 2.dp,
            color = if (enabled) borderColor else borderColor.copy(alpha = 0.38f)
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewBaseOutlinedButton() {
    JajanManiaTheme {
        Surface {
            BaseOutlinedButton(
                text = "Transfer Bank",
            )
        }
    }
}
