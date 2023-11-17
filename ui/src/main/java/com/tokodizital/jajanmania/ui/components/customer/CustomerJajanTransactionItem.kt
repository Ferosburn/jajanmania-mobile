package com.tokodizital.jajanmania.ui.components.customer

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.ui.theme.Typography

@Composable
fun CustomerJajanTransactionItem(
    modifier: Modifier = Modifier,
    jajan: JajanItem,
    count: Int
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
                // TODO: Use when integrated with backend
//                AsyncImage(
//                    model = jajan.image,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .fillMaxSize(),
//                    placeholder = painterResource(id = R.drawable.ic_image)
//                )
                // TODO: Delete when integrated with backend
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jajan.name,
                style = Typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jajan.price.toInt().toRupiah(),
                style = Typography.titleSmall
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = count.toString(),
                style = Typography.titleSmall
            )
            Spacer(modifier = Modifier.width(8.dp))

        }
        Divider()
    }
}