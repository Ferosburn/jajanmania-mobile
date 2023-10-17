package com.tokodizital.jajanmania.vendor.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.vendor.TransactionHistoryItem
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun HomeContentSection(
    modifier: Modifier = Modifier,
    history: List<TransactionHistory> = emptyList(),
    onTransactionClick: (TransactionHistory) -> Unit = {},
){
    Column(
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                fontSize = 16.sp,
                text = "Riwayat Transaksi"
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    fontSize = 12.sp,
                    text = "Lihat Lainnya",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .height(18.dp)
                )
                Image(
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_more),
                    contentDescription = "More",
                    contentScale = ContentScale.None
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        history.forEach{
            TransactionHistoryItem(transactionHistory = it, onClick = onTransactionClick)
        }
    }
}

@Preview
@Composable
fun PreviewHomeContentSection() {
    JajanManiaTheme {
        Surface {
            val history = (1..3).map {
                TransactionHistory(
                    transactionId = "ID-09723892$it",
                    vendorId = 1,
                    jajanId = 1,
                    price = 100000,
                    image = "",
                    status = "Pending",
                    createdAt = "2023-10-06T13:22:16.698Z"
                )
            }
            HomeContentSection(
                history = history
            )
        }
    }
}
