package com.tokodizital.jajanmania.ui.components.appbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onProfileClicked: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_jajan_mania_48),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(48.dp, 48.dp)
                    .padding(start = 4.dp)
            )
        },
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = onProfileClicked,
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
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