package ghazimoradi.soheil.digikala.ui.screens.user.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun ProfileOrdersSection(
    navController: NavHostController,
    orders: List<OrderFullDetail>
) {

    val waitForPurchaseOrders = orders.filter { orderFullDetail ->
        orderFullDetail.orderStatus == "0"
    }

    val purchasedOrders = orders.filter { orderFullDetail ->
        orderFullDetail.orderStatus == "1"
    }

    val deliveredOrders = orders.filter { orderFullDetail ->
        orderFullDetail.orderStatus == "2"
    }

    val canceledOrders = orders.filter { orderFullDetail ->
        orderFullDetail.orderStatus == "3"
    }

    val returnedOrders = orders.filter { orderFullDetail ->
        orderFullDetail.orderStatus == "4"
    }

    Text(
        color = MaterialTheme.colorScheme.darkText,
        modifier = Modifier.padding(MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.my_orders),
    )

    LazyRow(
        modifier = Modifier.clickable {
//            val ordersString = Gson().toJson(orders)
//            navController.navigate(
//                  Screen.TabLayoutScreen.route + "?orders=${ordersString}"
//            )
        },
    ) {
        item {
            ProfileOrdersItem(
                text = stringResource(id = R.string.unpaid),
                count = waitForPurchaseOrders.size,
                painter = painterResource(id = R.drawable.digi_unpaid)
            )
        }
        item {
            ProfileOrdersItem(
                text = stringResource(id = R.string.processing),
                count = purchasedOrders.size,
                painter = painterResource(id = R.drawable.digi_processing)
            )
        }
        item {
            ProfileOrdersItem(
                text = stringResource(id = R.string.my_orders),
                count = deliveredOrders.size,
                painter = painterResource(id = R.drawable.digi_delivered)
            )
        }
        item {
            ProfileOrdersItem(
                text = stringResource(id = R.string.canceled),
                count = canceledOrders.size,
                painter = painterResource(id = R.drawable.digi_cancel)
            )
        }
        item {
            ProfileOrdersItem(
                text = stringResource(id = R.string.returned),
                count = returnedOrders.size,
                painter = painterResource(id = R.drawable.digi_returned)
            )
        }
    }
}