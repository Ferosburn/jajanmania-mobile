package com.tokodizital.jajanmania.vendor.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.theme.poppins

@Composable
fun TransactionHistoryHeaderSection(
    modifier: Modifier = Modifier,
    onSeeOtherClicked: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            fontSize = 16.sp,
            text = "Riwayat Transaksi"
        )
        TextButton(
            onClick = onSeeOtherClicked,
            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp)
        ) {
            Text(
                text = "Lihat Lainnya",
                fontSize = 12.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos, contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}