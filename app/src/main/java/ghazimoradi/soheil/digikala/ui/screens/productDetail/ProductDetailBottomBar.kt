package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.data.model.basket.CartStatus
import ghazimoradi.soheil.digikala.data.model.productDetail.ProductDetail
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.util.DigitHelper
import ghazimoradi.soheil.digikala.util.DigitHelper.applyDiscount
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailBottomBar(
    item: ProductDetail,
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    var price = 0L
    item.price?.let {
        price = it
    }

    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }

    var isShowAddToBasket by remember {
        mutableStateOf(true)
    }
    var isLaunchedEffectCompleted by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.isItemExistInBasket(item._id ?: "").collectLatest {
            isShowAddToBasket = !it
            isLaunchedEffectCompleted = true
        }
    }

    var itemsCountInBasket by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(true) {
        viewModel.getItemsCountInBasket(item._id ?: "").collectLatest {
            itemsCountInBasket = it
        }
    }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomBar,
        modifier = Modifier.height(70.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.biggerSmall,
                    horizontal = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                if (isLaunchedEffectCompleted && isShowAddToBasket) {
                    Button(
                        onClick = {
                            isShowAddToBasket = false
                            viewModel.insertCartItem(
                                CartItem(
                                    item._id ?: "",
                                    item.name ?: "",
                                    item.seller ?: "",
                                    item.price ?: 0,
                                    item.discountPercent ?: 0,
                                    item.imageSlider?.get(0)?.image ?: "",
                                    1,
                                    CartStatus.CURRENT_CART
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.DigiKalaRed),
                        shape = MaterialTheme.roundedShape.small,

                        ) {
                        Text(
                            text = stringResource(R.string.add_to_basket),
                            color = White,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .padding(
                                    vertical = MaterialTheme.spacing.extraSmall,
                                )
                        )
                    }
                } else if (isLaunchedEffectCompleted && !isShowAddToBasket) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colors.DigiKalaDarkRed),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = DigitHelper.digitByLocate(itemsCountInBasket.toString()),
                                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                                color = White
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = stringResource(R.string.in_your_basket),
                                color = MaterialTheme.colors.gray,
                                style = MaterialTheme.typography.h5,
                            )
                            Text(
                                text = stringResource(R.string.view_in_cart),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.Basket.route)
                                    },
                                color = MaterialTheme.colors.DigiKalaDarkRed,
                                style = MaterialTheme.typography.h5,
                            )
                        }
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.DigiKalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),

                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(discountPercent.toString())}%",
                            color = White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                        )
                    }

                    Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))

                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price).toString()),
                        color = MaterialTheme.colors.gray,
                        style = MaterialTheme.typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }

                Row {
                    Text(
                        color = MaterialTheme.colors.darkText,
                        text = DigitHelper.digitByLocateAndSeparator(
                            applyDiscount(
                                price,
                                discountPercent
                            ).toString()
                        ),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Icon(
                        tint = MaterialTheme.colors.icon,
                        painter = logoChangeByLanguage(
                            enLogo = R.drawable.dollar,
                            faLogo = R.drawable.toman
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
    }
}