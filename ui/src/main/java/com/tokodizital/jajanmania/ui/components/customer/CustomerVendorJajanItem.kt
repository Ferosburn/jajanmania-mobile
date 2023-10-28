package com.tokodizital.jajanmania.ui.components.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Jajan

@Composable
fun CustomerVendorJajanItem(
    modifier: Modifier = Modifier,
    jajan: Jajan,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp, 48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = jajan.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = jajan.category,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(text = jajan.price.toRupiah(), style = MaterialTheme.typography.titleSmall)
        }
        Divider(thickness = 0.dp)
    }
}