package com.tokodizital.jajanmania.ui.components.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun TransactionStatus(
    modifier: Modifier = Modifier,
    status: String
) {
    Text(
        text = status,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFFF5EFF7),
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
    )
}

@Preview
@Composable
fun PreviewTransactionStatus() {
    JajanManiaTheme {
        Surface {
            TransactionStatus(
                status = "Pending",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
