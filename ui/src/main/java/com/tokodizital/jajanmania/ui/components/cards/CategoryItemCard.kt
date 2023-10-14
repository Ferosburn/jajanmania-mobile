package com.tokodizital.jajanmania.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun CategoryItemCard(
    name: String,
    icon: String = "",
    isSubscribed: Boolean,
) {
    Card(
        modifier = Modifier
            .size(height = 250.dp, width = 170.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDD671)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (icon.isBlank()) {
                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = "placeholder",
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            if (isSubscribed) {
                BaseOutlinedButton(
                    onClicked = {},
                    text = stringResource(id = R.string.unsubscribe),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                BaseButton(
                    text = stringResource(id = R.string.subscribe),
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color(0xFF343434)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryItemCard() {
    JajanManiaTheme {
        Surface {
            CategoryItemCard(
                name = "Soto",
                isSubscribed = true
            )
        }
    }
}