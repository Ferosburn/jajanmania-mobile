package com.tokodizital.jajanmania.ui.components.vendor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.components.state.TransactionStatus
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun TransactionHistoryItem(
    modifier: Modifier = Modifier,
    transactionHistory: TransactionHistory,
    onClick: (TransactionHistory) -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick(transactionHistory) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: This will use when integration will backend
//            AsyncImage(
//                model = jajan.image,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .width(72.dp)
//                    .height(48.dp),
//                placeholder = painterResource(id = R.drawable.ic_image)
//            )
            // TODO: This will delete when integration will backend
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(72.dp)
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transactionHistory.transactionId,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transactionHistory.createdAt,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = transactionHistory.price.toRupiah(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                TransactionStatus(status = transactionHistory.status)
            }
        }
        Divider()
    }
}

@Preview
@Composable
fun PreviewTransactionHistoryItem() {
    JajanManiaTheme {
        Surface {
            val transactionHistory = TransactionHistory(
                transactionId = "ID-09723892",
                vendorId = 1,
                jajanId = 1,
                price = 100000,
                image = "",
                status = "Pending",
                createdAt = "1 Okt 2023, 19:59"
            )
            TransactionHistoryItem(
                transactionHistory = transactionHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {}
            )
        }
    }
}
