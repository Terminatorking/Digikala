package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.ui.components.extentions.getListTypeFromGson

@Composable
fun TabLayoutScreen(
    navController: NavHostController,
    orders: String
) {
    var orderList by remember {
        mutableStateOf<List<OrderFullDetail>>(emptyList())
    }

    orderList = getListTypeFromGson<OrderFullDetail>(jsonString = orders)

    val pagerState = rememberPagerState(pageCount = { 5 })

    Column {
        TabLayoutTopAppBar(navController)
        Tabs(pagerState, orderList)
        TabsContent(pagerState, orderList)
    }
}