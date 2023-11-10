package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearbyError
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun BaseTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit = {},
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    fontSize: TextUnit = 14.sp,
    underline: Boolean = false,
    endIcon: @Composable () -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
            )
        ) {
            append(text)
        }
    }
    TextButton(
        onClick = onClicked,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
    ) {
        Text(
            text = annotatedString,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium
        )
        endIcon()
    }
}

@Preview
@Composable
fun PreviewBaseTextButton() {
    JajanManiaTheme {
        Surface {
            BaseTextButton(
                text = "Transfer Bank",
                contentColor = Color(0XFF17C05B),
                fontSize = 12.sp,
                endIcon = {
                    Icon(imageVector = Icons.Default.NearbyError, contentDescription = null)
                }
            )
        }
    }
}
