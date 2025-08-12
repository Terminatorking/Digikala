package ghazimoradi.soheil.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.data.models.basket.CartStatus
import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.buy.BuyProcessContinue
import ghazimoradi.soheil.digikala.ui.components.project.ProjectPullToRefresh
import ghazimoradi.soheil.digikala.ui.components.cartPriceDetail.CartPriceDetailSection
import ghazimoradi.soheil.digikala.ui.components.extentions.getScreenHeight
import ghazimoradi.soheil.digikala.ui.components.extentions.refreshDataFromServer
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.Constants.USER_TOKEN
import ghazimoradi.soheil.digikala.viewModels.BasketViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCart(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val cartDetail by viewModel.cartDetail.collectAsState()

    val currentCartItemsState: BasketScreenState<List<CartItem>> by viewModel.currentCartItems.collectAsState(
        BasketScreenState.Loading
    )

    var isCartEmpty by remember {
        mutableStateOf(true)
    }

    viewModel.getSuggestedItems()

    var suggestedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Dispatchers.Main) {
        viewModel.suggestedList.collectLatest { response ->
            when (response) {
                is NetworkResult.Success -> {
                    suggestedList = response.data ?: emptyList()
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("3636", "SuggestListSection error : ${response.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }

    ProjectPullToRefresh(
        isRefreshing = isRefreshing,
        onRefresh = {
            loading = true
            isRefreshing = true
            viewModel.refreshDataFromServer()
            coroutineScope.launch {
                delay(1500)
                isRefreshing = false
            }
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 60.dp),
                ) {

                    item {
                        if (USER_TOKEN == "null") {
                            LoginOrRegisterSection(navController)
                        }
                    }

                    when (currentCartItemsState) {
                        is BasketScreenState.Success -> {
                            if ((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                                isCartEmpty = true

                                item {
                                    EmptyBasketShopping()
                                }
                                item {
                                    SuggestListSection(
                                        navController = navController,
                                        loading = loading,
                                        suggestedList = suggestedList
                                    )
                                }

                            } else {
                                isCartEmpty = false

                                items((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data) { item ->
                                    CartItemCard(item, CartStatus.CURRENT_CART, navController)
                                }
                                item {
                                    CartPriceDetailSection(cartDetail)
                                }
                            }
                        }

                        is BasketScreenState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .height(getScreenHeight() - 60.dp)
                                        .fillMaxWidth()
                                        .padding(vertical = MaterialTheme.spacing.small),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.please_wait),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.h5,
                                        color = MaterialTheme.colorScheme.darkText,
                                    )
                                }
                            }
                        }

                        is BasketScreenState.Error -> {
                            Log.e("3636", "err")
                        }
                    }
                }

                if (!isCartEmpty) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(
                                start = MaterialTheme.spacing.biggerSmall,
                                end = MaterialTheme.spacing.biggerSmall,
                                bottom = MaterialTheme.spacing.extraLarge
                            )
                    ) {
                        BuyProcessContinue(cartDetail.payablePrice) {
                            if (USER_TOKEN == "null") {
                                navController.navigate(Screen.Profile.route)
                            } else {
                                navController.navigate(Screen.Checkout.route)
                            }
                        }
                    }
                }
            }
        },
    )
}