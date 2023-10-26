package com.tokodizital.jajanmania.customer.subscription

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Category
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.cards.CategoryItemCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {}
) {
    val CategoryTypeList: List<Category> = listOf(
        Category(
            name = "Soto",
            isSubscribed = true
        ),
        Category(
            name = "Bakso",
            isSubscribed = true
        ),
        Category(
            name = "Rawon",
            isSubscribed = true
        ),
        Category(
            name = "Soto Medan",
            isSubscribed = true
        ),
    )
    val CategoryTypeList2: List<Category> = listOf(
        Category(
            name = "Bakso Goreng",
            isSubscribed = false
        ),
        Category(
            name = "Batagor",
            isSubscribed = false
        ),
        Category(
            name = "Goreng",
            isSubscribed = false
        ),
        Category(
            name = "Pisang Goreng",
            isSubscribed = false
        ),
    )
    val CategoryTypeList3: List<Category> = listOf(
        Category(
            name = "Martabak Manis",
            isSubscribed = false
        ),
        Category(
            name = "Pisang Coklat",
            isSubscribed = false
        ),
        Category(
            name = "Putu bambu",
            isSubscribed = false
        ),
        Category(
            name = "Sang Pisang",
            isSubscribed = false
        ),
    )
    val CategoryTypeList4: List<Category> = listOf(
        Category(
            name = "Ketoprak",
            isSubscribed = false
        ),
        Category(
            name = "Tahu gejrot",
            isSubscribed = false
        ),
        Category(
            name = "Siomay",
            isSubscribed = false
        ),
        Category(
            name = "Gado-Gado",
            isSubscribed = false
        ),
    )
    Scaffold(
        topBar = { DetailTopAppBar(title = "Kategori", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 24.dp),
            modifier = modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                CategoryType(
                    title = "Makanan Berkuah",
                    list = CategoryTypeList
                )
            }
            item {
                CategoryType(
                    title = "Goreng",
                    list = CategoryTypeList2
                )
            }
            item {
                CategoryType(
                    title = "Manis-Manis",
                    list = CategoryTypeList3
                )
            }
            item {
                CategoryType(
                    title = "Asin Gurih",
                    list = CategoryTypeList4
                )
            }
        }
    }
}

@Composable
fun CategoryType(
    title: String = "Title",
    list: List<Category> = listOf()
) {
    Column {
        if (list.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium

                )
                Spacer(modifier = Modifier.width(32.dp))
                Row(
                    modifier = Modifier.clickable {},
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lihat Lainnya",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) { category ->
                    CategoryItemCard(name = category.name, isSubscribed = category.isSubscribed)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCategoryScreen() {
    JajanManiaTheme {
        Surface {
            CategoryScreen()
        }
    }
}