package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography

@Composable
fun DetailTransactionBody(
    tittle: String,
    body: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = tittle, style = Typography.bodyMedium)
        Text(text = body, style = Typography.bodyMedium)
    }

}


@Preview
@Composable
private fun Preview() {
    JajanManiaTheme{
        Surface {
            DetailTransactionBody(
                tittle = "Metode Pembayaran",
                body = "Non-Tunai")
        }
    }
}

