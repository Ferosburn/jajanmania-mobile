package com.tokodizital.jajanmania.vendor.shop.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun EmptyShopContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color(0xFF343434),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(horizontal = 32.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Belum Ada Produk!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Mohon tambahkan produk jajanan yang dijual",
            fontSize = 12.sp,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewEmptyShopContent() {
    JajanManiaTheme {
        Surface {
            EmptyShopContent(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
