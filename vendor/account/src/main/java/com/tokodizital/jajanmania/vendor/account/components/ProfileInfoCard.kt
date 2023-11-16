package com.tokodizital.jajanmania.vendor.account.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun ProfileInfoCard(
    jajanName: String,
    vendorName: String,
    username: String,
    level: String,
    imageUrl: String = ""
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
                .size(90.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_image),
            error = painterResource(id = R.drawable.ic_image)
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = jajanName,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = vendorName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            Text(
                text = username,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            Text(
                text = level,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    JajanManiaTheme {
        Surface {
            var profileName = rememberSaveable { mutableStateOf("Elon Musk") }
            var profileUsername = rememberSaveable { mutableStateOf("@username") }
            var profileEmail = rememberSaveable { mutableStateOf("ElonMusk@twitter.com") }
            var profileLevel = rememberSaveable { mutableStateOf("Level 1") }


            ProfileInfoCard(
                profileName.value,
                profileUsername.value,
                profileEmail.value,
                profileLevel.value
            )
        }
    }
}