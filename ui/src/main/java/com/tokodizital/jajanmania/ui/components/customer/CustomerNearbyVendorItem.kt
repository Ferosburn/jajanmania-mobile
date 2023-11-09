package com.tokodizital.jajanmania.ui.components.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult

@Composable
fun CustomerNearbyVendorItem(
    modifier: Modifier = Modifier,
    vendor: NearbyVendorResult,
    onClick: (NearbyVendorResult) -> Unit = {}
) {
    Column (
        modifier = modifier.clickable { onClick(vendor) }
    ) {
        Row(modifier = Modifier.padding(vertical = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(72.dp, 48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = vendor.name, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = vendor.description,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Divider(thickness = 0.dp)
    }
}