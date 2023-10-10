package com.tokodizital.jajanmania.vendor.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun EWalletBalanceSection(
    modifier: Modifier = Modifier,
    balance: Long = 0L
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_balance),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = Color(0xFF343434),
                        shape = RoundedCornerShape(size = 100.dp)
                    )
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Saldo",
                    fontSize = 11.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF343434)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = balance.toRupiah(),
                    fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF343434)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            BaseButton(
                text = "Transfer Bank",
                containerColor = Color(0xFF343434)
            )
        }
    }
}

@Preview
@Composable
fun PreviewEWalletBalanceSection() {
    JajanManiaTheme {
        Surface {
            EWalletBalanceSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                balance = 0
            )
        }
    }
}
