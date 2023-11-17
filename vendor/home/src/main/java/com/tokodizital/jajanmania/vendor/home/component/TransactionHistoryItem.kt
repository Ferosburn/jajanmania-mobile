package com.tokodizital.jajanmania.vendor.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.common.utils.transactionHistoryFormat
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.home.mapper.toDomain

@Composable
fun TransactionHistoryItem(
    modifier: Modifier = Modifier,
    transactionHistory: TransactionHistoryItem,
    onClick: (String) -> Unit
) {
    val transaction = transactionHistory.toDomain()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(transaction.transactionId) }
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = transaction.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(72.dp)
                    .height(48.dp),
                placeholder = painterResource(id = R.drawable.ic_image),
                error = painterResource(id = R.drawable.ic_image)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.paymentMethod,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transactionHistory.createdAt.transactionHistoryFormat(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = transaction.price.toRupiah(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
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
        }
    }
}
