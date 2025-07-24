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
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.data.model.basket.CartStatus
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel

@Composable
fun ShoppingCart(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val cartDetail by viewModel.cartDetail.collectAsState()

    val currentCartItemsState: BasketScreenState<List<CartItem>> by viewModel.currentCartItems
        .collectAsState(BasketScreenState.Loading)

    var isCartEmpty by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 60.dp),
        ) {

            when (currentCartItemsState) {
                is BasketScreenState.Success -> {
                    if ((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                        isCartEmpty = true

                        item {
                            EmptyBasketShopping()
                        }
                        item {
                            SuggestListSection(navController)
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
                                .height(LocalWindowInfo.current.containerSize.height.dp - 60.dp)
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.small),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.please_wait),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.darkText,
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
                    .padding(bottom = 60.dp)
            ) {
                BuyProcessContinue(cartDetail.payablePrice) {
//                    if (Constants.USER_TOKEN == "null") {
//                        navController.navigate(Screen.Profile.route)
//                    } else {
//                        navController.navigate(Screen.Checkout.route)
//                    }
                }
            }
        }
    }
}