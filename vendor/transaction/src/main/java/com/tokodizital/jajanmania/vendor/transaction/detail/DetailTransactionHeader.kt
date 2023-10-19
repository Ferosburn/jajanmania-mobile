package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
internal fun DetailTransactionHeader(
    modifier: Modifier = Modifier,
    photo: String,
    transactionCode: Long,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        AsyncImage(
//            model = photo,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(120.dp)
//                .clip(CircleShape)
//        )
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Kode Pembayaran:",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = transactionCode.toString(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewDetailTransactionHeader() {
    JajanManiaTheme {
        Surface {
            DetailTransactionHeader(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                photo = "",
                transactionCode = 19283719831,
            )
        }
    }
}
