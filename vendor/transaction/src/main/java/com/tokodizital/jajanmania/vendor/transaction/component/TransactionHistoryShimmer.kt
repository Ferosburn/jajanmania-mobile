package com.tokodizital.jajanmania.vendor.transaction.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun TransactionHistoryShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.2f))

    )
}

@Preview
@Composable
fun PreviewTransactionHistoryShimmer() {
    JajanManiaTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TransactionHistoryShimmer(
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
