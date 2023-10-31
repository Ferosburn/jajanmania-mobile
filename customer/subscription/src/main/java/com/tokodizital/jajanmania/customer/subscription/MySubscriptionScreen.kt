package com.tokodizital.jajanmania.customer.subscription

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Subscription
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.cards.CategoryItemCard
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MySubscriptionScreen(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {}
) {
    val SubsList: List<Subscription> = listOf(
        Subscription(
            name = "Soto",
            isSubscribed = true
        ),
        Subscription(
            name = "Bakso",
            isSubscribed = true
        ),
        Subscription(
            name = "Es Lilin",
            isSubscribed = true
        ),
        Subscription(
            name = "Soto Medan",
            isSubscribed = true
        ),Subscription(
            name = "Soto Medan",
            isSubscribed = true
        ),Subscription(
            name = "Soto Medan",
            isSubscribed = true
        ),Subscription(
            name = "Soto Medan",
            isSubscribed = true
        )
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Langgananku", onNavigationClicked = onNavigationClick)
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(top = 26.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (SubsList.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(bottom = 24.dp)
                        .fillMaxSize()
                        .padding(16.dp) // Add padding to the LazyVerticalGrid
                ) {
                    items(SubsList) { subscription ->
                        MySubscriptionItem(subscription)
                    }
                }
            } else {
                // Show the empty state message
                EmptyContentState(
                            modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                            title = "Kamu Belum Berlangganan Notifikasi",
                            description = "Yuk subscribe kategori jajanan yang kamu sukai untuk berlangganan notifikasi!"
                        )
                    }
                }
            }
        }

@ExperimentalMaterial3Api
@Composable
fun MySubscriptionItem(
    subscription: Subscription
) {
    Column {
        if (subscription.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            CategoryItemCard(name = subscription.name, isSubscribed = subscription.isSubscribed)
        }
    }
}

fun Subscription.isNotEmpty(): Boolean {
    return this.name.isNotEmpty()
}



@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewMySubscriptionScreen() {
    JajanManiaTheme {
        Surface {
            MySubscriptionScreen()
        }
    }
}