package com.tokodizital.jajanmania.vendor.transaction.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography


@Composable
fun TotalTransactionFooter(
    modifier: Modifier = Modifier,
    totalPrice: Double
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.width(64.dp))
        Text(
            text = "Total",
            style = Typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = totalPrice.toRupiah(),
            style = Typography.titleSmall
        )
        Spacer(modifier = Modifier.width(32.dp))
    }
}

@Preview
@Composable
fun PreviewTotalTransactionFooter() {
    JajanManiaTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            TotalTransactionFooter(totalPrice = 120000.0)
        }
    }
}