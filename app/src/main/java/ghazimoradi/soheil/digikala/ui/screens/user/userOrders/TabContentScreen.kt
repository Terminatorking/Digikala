package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.settingArrow
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.DigitHelper

@Composable
fun TabContentScreen(orders: List<OrderFullDetail>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.searchBarBg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (orders.size > 0) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(orders) { item ->
                    Card(
                        shape = MaterialTheme.roundedShape.small,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                MaterialTheme.spacing.extraSmall
                            )
                    ) {
                        Column {
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.small),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "${stringResource(R.string.order_code)} ${
                                        item._id.takeLast(10)
                                    }",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h5,
                                    color = MaterialTheme.colorScheme.darkText,
                                )

                                Row() {
                                    Text(
                                        text = DigitHelper.digitBySeparator(
                                            DigitHelper.digitByLocate(
                                                item.orderTotalPrice.toString()
                                            )
                                        )
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.toman),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(MaterialTheme.spacing.semiLarge)
                                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                                    )
                                }
                            }

                            val dateParts = item.updatedAt.substringBefore("T").split("-")
                            val year = dateParts[0].toInt()
                            val month = dateParts[1].toInt()
                            val day = dateParts[2].toInt()

                            Text(
                                text = DigitHelper.digitByLocate(
                                    DigitHelper.gregorianToJalali(
                                        year,
                                        month,
                                        day
                                    )
                                ),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colorScheme.semiDarkText,
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.small
                                )
                            )

                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.small),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){

                                Row(Modifier.padding(vertical = MaterialTheme.spacing.small)){
                                    for (product in item.orderProducts.takeLast(4)){
                                        Image(
                                            painter = rememberAsyncImagePainter(product.image),
                                            contentDescription = "",
                                            modifier = Modifier.size(50.dp)
                                        )
                                    }
                                }

                                Icon(
                                    Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(top = MaterialTheme.spacing.medium)
                                        .size(26.dp),
                                    tint = MaterialTheme.colorScheme.settingArrow
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}