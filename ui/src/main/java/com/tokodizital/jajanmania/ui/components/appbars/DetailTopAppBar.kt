package com.tokodizital.jajanmania.ui.components.appbars

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun DetailTopAppBar(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClicked) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor
        ),
        actions = actions,
        modifier = modifier
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailTopAppBar() {
    JajanManiaTheme {
        Surface {
            DetailTopAppBar(title = "Detail")
        }
    }
}
