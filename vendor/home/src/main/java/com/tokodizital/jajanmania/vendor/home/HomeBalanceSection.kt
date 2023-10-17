package com.tokodizital.jajanmania.vendor.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.HomeMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun HomeBalanceSection(
    modifier: Modifier = Modifier,
    menu: List<HomeMenu> = emptyList(),
    onMenuClicked: (HomeMenu) -> Unit = {},
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
            verticalAlignment = Alignment.CenterVertically,
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
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF343434)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = balance.toRupiah(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF343434)
                )
            }
            menu.forEach {
                HomeMenuItem(menu = it, onClicked = onMenuClicked)
            }
        }
    }
}

@Composable
fun HomeMenuItem(
    modifier: Modifier = Modifier,
    menu: HomeMenu,
    onClicked: (HomeMenu) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = stringResource(id = menu.label),
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFF343434))
                .clickable { onClicked(menu) }
                .width(34.dp)
                .padding(8.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = menu.label),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewHomeBalanceSection() {
    JajanManiaTheme {
        Surface {
            val menu = (1..3).map {
                HomeMenu(
                    icon = R.drawable.ic_balance,
                    label = R.string.label_others
                )
            }
            HomeBalanceSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                menu = menu,
                balance = 0
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeMenuItem() {
    JajanManiaTheme {
        Surface {
            val menu = HomeMenu(
                icon = R.drawable.ic_balance,
                label = R.string.label_kasir
            )
            HomeMenuItem(
                menu = menu
            )
        }
    }
}
