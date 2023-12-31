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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun JajanTransactionItem(
    modifier: Modifier = Modifier,
    jajan: Jajan,
    count: Int,
    totalPrice: Int,
    onClick: (Jajan) -> Unit,
    onDecreaseClicked: (Jajan) -> Unit,
    onIncreaseClicked: (Jajan) -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick(jajan) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: This will use when integration will backend
//            AsyncImage(
//                model = jajan.image,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .width(48.dp)
//                    .height(48.dp),
//                placeholder = painterResource(id = R.drawable.ic_image)
//            )
            // TODO: This will delete when integration will backend
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(48.dp)
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jajan.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = totalPrice.toRupiah(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(16.dp))
            JajanTransactionItemCounter(
                count = count,
                onDecreaseClicked = { onDecreaseClicked(jajan) },
                onIncreaseClicked = { onIncreaseClicked(jajan) }
            )
        }
        Divider()
    }
}

@Composable
private fun JajanTransactionItemCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onDecreaseClicked: () -> Unit = {},
    onIncreaseClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onDecreaseClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove_18),
                contentDescription = "Kurangi"
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = count.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF343434),
        )
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = onIncreaseClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_18),
                contentDescription = "Tambah"
            )
        }
    }
}

@Preview
@Composable
fun PreviewJajanTransactionItem() {
    JajanManiaTheme {
        Surface {
            val jajan = Jajan(
                id = "1",
                vendorId = "1",
                name = "Soto",
                category = "Makanan Kuah",
                price = 120000L,
                image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
            )
            JajanTransactionItem(
                jajan = jajan,
                count = 1,
                totalPrice = 100000,
                onClick = {},
                onDecreaseClicked = {},
                onIncreaseClicked = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}
