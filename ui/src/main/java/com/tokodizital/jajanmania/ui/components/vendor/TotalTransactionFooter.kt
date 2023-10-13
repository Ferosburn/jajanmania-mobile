package com.tokodizital.jajanmania.ui.components.vendor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun TotalTransactionFooter(
    modifier: Modifier = Modifier,
    totalPrice: Int
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = "Total",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = totalPrice.toRupiah(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewTotalTransactionFooter() {
    JajanManiaTheme {
        Surface {
            TotalTransactionFooter(
                totalPrice = 100_000
            )
        }
    }
}
