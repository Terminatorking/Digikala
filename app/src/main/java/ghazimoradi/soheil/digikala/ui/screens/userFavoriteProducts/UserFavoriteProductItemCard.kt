package ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.prfile.FavItem
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaDarkRed
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.amber
import ghazimoradi.soheil.digikala.ui.theme.body2
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.PERSIAN_LANG
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE
import ghazimoradi.soheil.digikala.util.DigitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun UserFavoriteProductItemCard(
    navController: NavHostController,
    favItem: FavItem,
    coroutineScope: CoroutineScope,
    modalSheetState: ModalBottomSheetState,
    onItemSelected: (FavItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.ProductDetail.withArgs(favItem.id))
            },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(favItem.image),
                contentDescription = "",
                modifier = Modifier
                    .width(120.dp)
                    .height(100.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = MaterialTheme.spacing.medium),
                    text = favItem.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.in_stock),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colorScheme.cyan
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = favItem.seller,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colorScheme.semiDarkText,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = DigitHelper.digitByLocate(favItem.star.toString()),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colorScheme.semiDarkText,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_star),
                            contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(2.dp),
                            tint = MaterialTheme.colorScheme.amber
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colorScheme.DigiKalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),
                    ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(favItem.discountPercent.toString())}%",
                            color = White,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Column {
                        Row {
                            Text(
                                text = DigitHelper.digitBySeparator(
                                    DigitHelper.digitByLocate(
                                        DigitHelper.applyDiscount(
                                            favItem.price, favItem.discountPercent
                                        ).toString()
                                    )
                                ),
                                color = MaterialTheme.colorScheme.darkText,
                                style = MaterialTheme.typography.body2,
                            )

                            Icon(
                                tint = MaterialTheme.colorScheme.icon,
                                painter = logoChangeByLanguage(
                                    enLogo = R.drawable.dollar,
                                    faLogo = R.drawable.toman,
                                ),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(MaterialTheme.spacing.semiLarge)
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )
                        }
                        Text(
                            color = MaterialTheme.colorScheme.darkText,
                            textDecoration = TextDecoration.LineThrough,
                            text = DigitHelper.digitBySeparator(
                                DigitHelper.digitByLocate(
                                    favItem.price.toString()
                                )
                            )
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(0.5f)
                            .clickable {
                                coroutineScope.launch {
                                    onItemSelected(favItem)
                                    if (modalSheetState.isVisible) {
                                        modalSheetState.hide()
                                    } else {
                                        modalSheetState.show()
                                    }
                                }
                            },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.digi_trash),
                            contentDescription = "digi trash",
                            tint = MaterialTheme.colorScheme.DigiKalaRed
                        )
                        Text(
                            text = stringResource(id = R.string.remove_from_list),
                            color = MaterialTheme.colorScheme.DigiKalaRed,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h6
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text(
                            text = stringResource(R.string.watch_and_buy_product),
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colorScheme.cyan
                        )
                        if (USER_LANGUAGE == PERSIAN_LANG) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.cyan
                            )
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier
                        .alpha(0.2f)
                        .padding(MaterialTheme.spacing.small),
                    color = MaterialTheme.colorScheme.gray
                )
            }
        }
    }
}