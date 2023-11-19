package com.tokodizital.jajanmania.vendor.transaction.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.JajanItem
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography

@Composable
fun JajanTransactionItem(
    modifier: Modifier = Modifier,
    jajanItem: JajanItem,
    quantity: Int
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
            ) {
                AsyncImage(
                    model = jajanItem.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.ic_image),
                    error = painterResource(id = R.drawable.ic_image)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jajanItem.name,
                style = Typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jajanItem.price.toRupiah(),
                style = Typography.titleSmall
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = quantity.toString(),
                style = Typography.titleSmall
            )
            Spacer(modifier = Modifier.width(8.dp))

        }
        Divider()
    }
}

@Preview
@Composable
fun PreviewJajanTransactionItem() {
    val jajan = Jajan(
        id = "1",
        vendorId = "1",
        name = "Soto",
        category = "Makanan Kuah",
        price = 120000L,
        image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
    )
    JajanManiaTheme {
        Surface {
//            CustomerJajanTransactionItem(
//                transaction = jajan,
//                count = 2,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
        }
    }
}