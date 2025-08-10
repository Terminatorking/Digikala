package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail

@Suppress("Deprecation")
@Composable
fun TabLayoutScreen(
    navController: NavHostController,
    orders: String
) {
    var orderList by remember {
        mutableStateOf<List<OrderFullDetail>>(emptyList())
    }

    val orderListType = object : TypeToken<List<OrderFullDetail>>() {}.type
    orderList = Gson().fromJson(orders, orderListType)

    val pagerState = rememberPagerState()

    Column {
        TabLayoutTopAppBar(navController)
        Tabs(pagerState, orderList)
        TabsContent(pagerState, orderList)
    }
}