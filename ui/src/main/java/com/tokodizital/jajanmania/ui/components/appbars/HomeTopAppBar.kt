package com.tokodizital.jajanmania.ui.components.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title = { Text(text = "Jajan Mania") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Home, "Jajan Mania")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Person, "account")
            }
        })
}