package com.tokodizital.jajanmania.vendor.shop.component

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.ui.R

@Composable
fun ImageProductSection(
    modifier: Modifier = Modifier,
    image: Uri = Uri.EMPTY,
    openGallery: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 2.dp, color = Color(0xFF343434), RoundedCornerShape(10.dp))
            .clickable { openGallery() },
        contentAlignment = Alignment.Center
    ) {
        if (image == Uri.EMPTY) {
            AsyncImage(
                model = R.drawable.ic_image,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        } else {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
