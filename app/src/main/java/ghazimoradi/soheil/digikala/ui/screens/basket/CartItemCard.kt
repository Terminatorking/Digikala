package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.data.model.basket.CartStatus
import ghazimoradi.soheil.digikala.ui.components.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.DarkCyan
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaLightRed
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.Green
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.ui.theme.splashBg
import ghazimoradi.soheil.digikala.ui.theme.veryExtraSmall
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocateAndSeparator
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel

@Composable
fun CartItemCard(
    item: CartItem,
    mode: CartStatus,
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val count = remember {
        mutableIntStateOf(item.count)
    }

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colors.searchBarBg),
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.biggerSmall)
            .clickable {
                //  navController.navigate(Screen.ProductDetail.withArgs(item.itemId))
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.your_shopping_list),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.darkText
                    )
                    Text(
                        text = "${digitByLocateAndSeparator(count.intValue.toString())} " +
                                stringResource(R.string.goods),

                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.darkText
                    )
                }

                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = MaterialTheme.colors.icon
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "cart item Photo",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(.3f),
                )

                Column(modifier = Modifier.weight(.7f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall)
                    )

                    DetailRow(
                        painterResource(id = R.drawable.warranty),
                        stringResource(id = R.string.warranty),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )

                    DetailRow(
                        painterResource(id = R.drawable.store),
                        stringResource(id = R.string.digikala),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )

                    Row {
                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(vertical = MaterialTheme.spacing.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colors.DarkCyan
                            )

                            VerticalDivider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = MaterialTheme.colors.gray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )

                            VerticalDivider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = MaterialTheme.colors.gray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )
                        }

                        Column(Modifier.padding(horizontal = 8.dp)) {

                            Text(
                                text = item.seller,
                                style = MaterialTheme.typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.semiDarkText,
                                modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k1),
                                stringResource(id = R.string.digikala_send),
                                color = MaterialTheme.colors.splashBg,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k2),
                                stringResource(id = R.string.fast_send),
                                color = MaterialTheme.colors.Green,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    modifier = Modifier
                        .clip(MaterialTheme.roundedShape.semiSmall)
                        .border(
                            1.dp, Color.LightGray.copy(0.6f),
                            MaterialTheme.roundedShape.semiSmall,
                        )
                ) {
                    if (mode == CartStatus.CURRENT_CART) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.small,
                                vertical = MaterialTheme.spacing.extraSmall
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painterResource(id = R.drawable.ic_increase_24),
                                contentDescription = "increase icon",
                                tint = MaterialTheme.colors.DigiKalaRed,
                                modifier = Modifier.clickable {
                                    count.intValue++
                                    viewModel.changeCartItemCount(item.itemId, count.intValue)
                                },
                            )

                            Text(
                                text = digitByLocateAndSeparator(count.intValue.toString()),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.DigiKalaRed,
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                            )

                            if (count.intValue == 1) {
                                Icon(
                                    painterResource(id = R.drawable.digi_trash),
                                    contentDescription = "increase icon",
                                    tint = MaterialTheme.colors.DigiKalaRed,
                                    modifier = Modifier.clickable {
                                        viewModel.removeCartItem(item)
                                    },
                                )
                            } else {
                                Icon(
                                    painterResource(id = R.drawable.ic_decrease_24),
                                    contentDescription = "increase icon",
                                    tint = MaterialTheme.colors.DigiKalaRed,
                                    modifier = Modifier.clickable {
                                        count.intValue--
                                        viewModel.changeCartItemCount(item.itemId, count.intValue)
                                    },
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 48.dp, vertical = MaterialTheme.spacing.small
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "increase icon",
                                tint = MaterialTheme.colors.DigiKalaRed,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        viewModel.changeCartItemStatus(
                                            item.itemId, CartStatus.CURRENT_CART
                                        )
                                    },
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.padding(MaterialTheme.spacing.semiMedium)
                )

                val discountAmount = (item.price * item.discountPercent) / 100

                Column {
                    Text(
                        text = digitByLocateAndSeparator(discountAmount.toString()) +
                                " ${stringResource(id = R.string.discount)}",

                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.DigiKalaRed,
                    )
                    Row {
                        Text(
                            text = digitByLocateAndSeparator(item.price.toString()),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )

                        Icon(
                            tint = MaterialTheme.colors.icon,
                            painter = logoChangeByLanguage(
                                enLogo = R.drawable.dollar,
                                faLogo = R.drawable.toman
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(MaterialTheme.spacing.extraSmall)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            if (mode == CartStatus.CURRENT_CART) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.changeCartItemStatus(item.itemId, CartStatus.NEXT_CART)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.save_to_next_list),
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DarkCyan
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.removeCartItem(item)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.delete_from_list),
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DigiKalaLightRed
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DigiKalaLightRed
                    )
                }
            }
        }
    }
}


@Composable
fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fontStyle: TextStyle,
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.size(16.dp),
            tint = color,
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = text,
            style = fontStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.semiDarkText
        )
    }
}