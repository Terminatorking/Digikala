package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun TabContentScreen(orders: List<OrderFullDetail>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (orders.isNotEmpty()) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(orders) { item ->
                   TabContentCard(item)
                }
            }
        }
    }
}