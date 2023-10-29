package com.tokodizital.jajanmania.customer.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.NearbyVendor
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.customer.CustomerNearbyVendorItem

@Composable
fun HomeNearbyVendorSection(
    modifier: Modifier = Modifier,
    list: List<NearbyVendor>,
    onMoreClick: () -> Unit = {},
    navigateToVendorDetailScreen: () -> Unit = {}
) {
    val onItemClick: (NearbyVendor) -> Unit = {
        navigateToVendorDetailScreen()
    }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.title_nearby_vendor),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(32.dp))
            Row(
                modifier = Modifier.clickable(onClick = onMoreClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.see_more),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column {
            list.map { vendor ->
                CustomerNearbyVendorItem(
                    vendor = vendor,
                    onClick = onItemClick
                )
            }
        }
    }
}