package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.data.models.prfile.TabItem
import ghazimoradi.soheil.digikala.ui.theme.digiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.font_standard
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.utils.DigitHelper
import kotlinx.coroutines.launch

@Composable
fun Tabs(
    pagerState: PagerState,
    orders: List<OrderFullDetail>
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(
        TabItem(
            "${stringResource(id = R.string.waiting_for_purchase)} (${
                DigitHelper.digitByLocate(
                    orders.filter {
                        it.orderStatus == "0"
                    }.size.toString()
                )
            })"
        ) {
            TabContentScreen(orders = orders.filter { it.orderStatus == "0" })
        },

        TabItem(
            "${stringResource(id = R.string.processing_orders)} (${
                DigitHelper.digitByLocate(
                    orders.filter {
                        it.orderStatus == "1"
                    }.size.toString()
                )
            })"
        ) {
            TabContentScreen(orders = orders.filter { it.orderStatus == "1" })
        },


        TabItem(
            "${stringResource(id = R.string.delivered_orders)} (${
                DigitHelper.digitByLocate(
                    orders.filter {
                        it.orderStatus == "2"
                    }.size.toString()
                )
            })"
        ) {
            TabContentScreen(orders = orders.filter { it.orderStatus == "2" })
        },


        TabItem(
            "${stringResource(id = R.string.canceled_orders)} (${
                DigitHelper.digitByLocate(
                    orders.filter {
                        it.orderStatus == "3"
                    }.size.toString()
                )
            })"
        ) {
            TabContentScreen(orders = orders.filter { it.orderStatus == "3" })
        },


        TabItem(
            "${stringResource(id = R.string.returned_orders)} (${
                DigitHelper.digitByLocate(
                    orders.filter {
                        it.orderStatus == "4"
                    }.size.toString()
                )
            })"
        ) {
            TabContentScreen(orders = orders.filter { it.orderStatus == "4" })
        },
    )

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.searchBarBg,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[pagerState.currentPage]
                ),
                height = 2.dp,
                color = MaterialTheme.colorScheme.digiKalaRed
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                text = {
                    Text(
                        text = tabs[index].title,
                        color = if (pagerState.currentPage == index)
                            MaterialTheme.colorScheme.digiKalaRed
                        else MaterialTheme.colorScheme.darkText,

                        fontFamily = font_standard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.wrapContentWidth()
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}