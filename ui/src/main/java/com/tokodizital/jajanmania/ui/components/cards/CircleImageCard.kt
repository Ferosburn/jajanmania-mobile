package com.tokodizital.jajanmania.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun CircleImageCard(
    dpSize: Int,
    painter: Painter
){
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(dpSize.dp)
    ) {
        Image(painter, contentDescription = "")
    }
}

@Preview
@Composable
private fun Preview() {
    JajanManiaTheme {
        Surface {
            val painter = painterResource(id = R.drawable.placeholder)
            CircleImageCard(100, painter)
        }
    }
}