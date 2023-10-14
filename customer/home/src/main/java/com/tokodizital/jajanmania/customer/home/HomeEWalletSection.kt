package com.tokodizital.jajanmania.customer.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.ui.R

@Composable
fun EWalletHomeSection(
    modifier: Modifier = Modifier,
    balance: Long = 0L
) {
    val listOfMenu: List<EWalletMenu> = listOf(
        EWalletMenu(
            icon = R.drawable.ic_bayar,
            label = R.string.label_bayar
        ),
        EWalletMenu(
            icon = R.drawable.ic_add,
            label = R.string.label_topUp
        ),
        EWalletMenu(
            icon = R.drawable.ic_more_vertical,
            label = R.string.label_others
        ),
    )

    Card(
        modifier = modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDD671)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp, 12.dp),
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
                    .padding(8.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Saldo", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = balance.toRupiah(), style = MaterialTheme.typography.titleSmall)
            }
            Row (horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                listOfMenu.map { eWalletMenu ->
                    EWalletHomeMenuItem(
                        menu = eWalletMenu
                    )
                }
            }
        }
    }
}

@Composable
fun EWalletHomeMenuItem(
    modifier: Modifier = Modifier,
    menu: EWalletMenu,
    onClicked: (EWalletMenu) -> Unit = {}
) {
    Column(
        modifier = modifier
            .requiredWidth(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = stringResource(id = menu.label),
            modifier = Modifier
                .size(34.dp, 34.dp)
                .clip(CircleShape)
                .background(Color(0xFF343434))
                .padding(8.dp)
                .clickable { onClicked(menu) },
            tint = Color.White,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = menu.label),
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}