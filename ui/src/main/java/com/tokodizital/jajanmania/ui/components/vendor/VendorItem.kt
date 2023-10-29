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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Vendor
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun VendorItem(
    modifier: Modifier = Modifier,
    vendor: Vendor,
    onClick: (Vendor) -> Unit
) {

    Column(
        modifier = modifier.clickable { onClick(vendor) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: This will use when integration will backend
//            AsyncImage(
//                model = vendor.jajanImage,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .width(72.dp)
//                    .height(48.dp),
//                placeholder = painterResource(id = R.drawable.ic_image)
//            )
            // TODO: This will delete when integration will backend
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(72.dp)
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vendor.jajanName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = vendor.jajanDescription,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Divider()
    }
}

@Preview
@Composable
fun PreviewCustomerTransactionHistoryItem() {
    JajanManiaTheme {
        Surface {
            val vendor = Vendor(
                id = "fbe68aec-8119-42a9-a820-ef7e6ebf2f20",
                fullname = "Vendor Name",
                gender = "MALE",
                address = "Jl Melati no 9",
                username = "dummyUsername",
                email = "dummyemail@email.com",
                balance = 0,
                experience = 0,
                jajanImage = "",
                jajanName = "Pisang Keju Pak Eko",
                jajanDescription = "Spesialis pisang keju di kota Kotok yang memberi pelayanan terbaik di kelasnya",
                status = "ON",
                lastLat = 0.0,
                lastLng = 1.0
            )
            VendorItem(
                vendor = vendor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {}
            )
        }
    }
}