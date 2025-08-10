@file:Suppress("Deprecation")

package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail

@Composable
fun TabsContent(pagerState: PagerState, orders: List<OrderFullDetail>) {

    HorizontalPager(state = pagerState, count = 5) { page ->
        when (page) {
            0 -> TabContentScreen(orders.filter { it.orderStatus == "0" })
            1 -> TabContentScreen(orders.filter { it.orderStatus == "1" })
            2 -> TabContentScreen(orders.filter { it.orderStatus == "2" })
            3 -> TabContentScreen(orders.filter { it.orderStatus == "3" })
            4 -> TabContentScreen(orders.filter { it.orderStatus == "4" })
        }
    }
}