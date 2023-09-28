package com.tokodizital.jajanmania.customer.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.appbars.BottomNavigationBar
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.cards.CategoryItemCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val navItems = listOf("Home", "Kategori", "Bayar", "Favorit", "Akun")
    Scaffold(
        topBar = { HomeTopAppBar() },
        modifier = modifier,
        bottomBar = { BottomNavigationBar(navItems = navItems)}
    ) { paddingValues ->
        HomeContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier) {
        items(4) {
            CategoryCollection()
        }
    }
}

@Composable
private fun CategoryCollection() {
    Column {
        Text(text = "Collection Title", modifier = Modifier.padding(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(5) {
                CategoryItemCard()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview
@Composable
private fun PreviewHomeContent() {
    JajanManiaTheme {
        Surface {
            HomeContent()
        }
    }
}

@Preview
@Composable
private fun JajanManiaHomeAppBarPreview() {
    HomeTopAppBar()
}

@Preview
@Composable
private fun CategoryItemPreview() {
    CategoryItemCard()
}
