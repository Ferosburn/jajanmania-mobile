package com.tokodizital.jajanmania.ui.components.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.ui.R

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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                AsyncImage(
                    model = jajan.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.ic_jajan_mania_48),
                    error = painterResource(id = R.drawable.ic_jajan_mania_48),
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = jajan.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = jajan.price.toInt().toRupiah(),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.width(64.dp),
                textAlign = TextAlign.Center
            )
        }
        Divider()
    }
}