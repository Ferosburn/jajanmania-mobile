package com.tokodizital.jajanmania.vendor.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun EWalletMenuSection(
    modifier: Modifier = Modifier,
    menu: List<EWalletMenu> = emptyList(),
    onMenuClicked: (EWalletMenu) -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        menu.forEach {
            EWalletMenuItem(menu = it, onClicked = onMenuClicked)
        }
    }
}

@Composable
fun EWalletMenuItem(
    modifier: Modifier = Modifier,
    menu: EWalletMenu,
    onClicked: (EWalletMenu) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .requiredWidth(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = stringResource(id = menu.label),
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFF343434))
                .clickable { onClicked(menu) }
                .padding(8.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = menu.label),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}

@Preview
@Composable
fun PreviewEWalletMenuSection() {
    JajanManiaTheme {
        Surface {
            val menu = (1..4).map {
                EWalletMenu(
                    icon = R.drawable.ic_balance,
                    label = R.string.total_balance_test
                )
            }
            EWalletMenuSection(
                menu = menu,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewEWalletMenuItem() {
    JajanManiaTheme {
        Surface {
            val menu = EWalletMenu(
                icon = R.drawable.ic_balance,
                label = R.string.total_balance_test
            )
            EWalletMenuItem(
                menu =menu
            )
        }
    }
}
