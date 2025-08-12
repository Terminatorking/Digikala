package ghazimoradi.soheil.digikala.ui.screens.products.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.data.models.basket.CartStatus
import ghazimoradi.soheil.digikala.data.models.productDetail.ProductDetail
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.extentions.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.digiKalaDarkRed
import ghazimoradi.soheil.digikala.ui.theme.digiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.body1
import ghazimoradi.soheil.digikala.ui.theme.body2
import ghazimoradi.soheil.digikala.ui.theme.bottomBar
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.DigitHelper
import ghazimoradi.soheil.digikala.utils.DigitHelper.applyDiscount
import ghazimoradi.soheil.digikala.viewModels.BasketViewModel
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

    var shouldShowAddToBasket by remember {
        mutableStateOf(true)
    }
    var isLaunchedEffectCompleted by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.isItemExistInBasket(item._id ?: "").collectLatest {
            shouldShowAddToBasket = !it
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

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.bottomBar,
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
                if (isLaunchedEffectCompleted && shouldShowAddToBasket) {
                    Button(
                        onClick = {
                            shouldShowAddToBasket = false
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
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.digiKalaRed),
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
                } else if (isLaunchedEffectCompleted && !shouldShowAddToBasket) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colorScheme.digiKalaDarkRed),
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
                                color = MaterialTheme.colorScheme.gray,
                                style = MaterialTheme.typography.h5,
                            )
                            Text(
                                text = stringResource(R.string.view_in_cart),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.Basket.route)
                                    },
                                color = MaterialTheme.colorScheme.digiKalaDarkRed,
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
                                color = MaterialTheme.colorScheme.digiKalaDarkRed,
                                shape = CircleShape
                            ).wrapContentWidth(Alignment.CenterHorizontally)
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
                        color = MaterialTheme.colorScheme.gray,
                        style = MaterialTheme.typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }

                Row {
                    Text(
                        color = MaterialTheme.colorScheme.darkText,
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
                        tint = MaterialTheme.colorScheme.icon,
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