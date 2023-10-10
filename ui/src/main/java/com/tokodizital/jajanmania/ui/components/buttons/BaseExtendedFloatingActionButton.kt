package com.tokodizital.jajanmania.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun BaseExtendedFloatingActionButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        if (icon != null) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
        }
        Text(text = text)
    }
}

@Preview
@Composable
fun PreviewBaseExtendedFloatingActionButton() {
    JajanManiaTheme {
        Surface {
            BaseExtendedFloatingActionButton(
                icon = R.drawable.ic_add,
                text = "Tambah Produk",
                onClick = {}
            )
        }
    }
}
