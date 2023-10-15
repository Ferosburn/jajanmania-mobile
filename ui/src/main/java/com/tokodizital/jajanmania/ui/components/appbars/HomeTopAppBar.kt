package com.tokodizital.jajanmania.ui.components.appbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFDD671)
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_jajan_mania_48),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(48.dp, 48.dp)
                    .padding(start = 4.dp)
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = Color(0xFF343434),
                            shape = RoundedCornerShape(size = 100.dp)
                        )
                        .padding(8.dp),
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewHomeTopAppBar() {
    JajanManiaTheme {
        Surface {
            HomeTopAppBar()
        }
    }
}