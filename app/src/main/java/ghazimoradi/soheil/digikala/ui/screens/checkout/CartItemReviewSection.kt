package ghazimoradi.soheil.digikala.ui.screens.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.IconWithRotate
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaLightRedText
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocate

@Composable
fun CartItemReviewSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            color = MaterialTheme.colors.darkText,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .align(Alignment.Start),
            text = stringResource(id = R.string.deliveryAndTimeMethod),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colors.searchBarBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            shape = MaterialTheme.roundedShape.small,
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.semiMedium)
            ) {

                Text(
                    color = MaterialTheme.colors.darkText,
                    text = digitByLocate(stringResource(id = R.string.delivery_1)),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.medium)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colors.DigiKalaLightRedText,
                        painter = painterResource(id = R.drawable.k1),
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Text(
                        text = stringResource(id = R.string.fast_send),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.extraSmall,
                        color = MaterialTheme.colors.DigiKalaLightRedText,
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                    Text(
                        text = digitByLocate("10000 ${stringResource(id = R.string.goods)} "),
                        Modifier.padding(MaterialTheme.spacing.small),
                        style = MaterialTheme.typography.extraSmall,
                        color = MaterialTheme.colors.darkText,
                    )
                }
//
//                LazyRow(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    items(currentCartItems) { item ->
//                        CheckoutProductCard(item)
//                    }
//                }

                Row {
                    Text(
                        color = MaterialTheme.colors.darkText,
                        text = stringResource(id = R.string.ready_to_send),
                        style = MaterialTheme.typography.extraSmall,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium),
                    )

                    Text(
                        text = " : ${stringResource(id = R.string.pishtaz_post)} (${
                            stringResource(
                                id = R.string.delivery_delay
                            )
                        })",
                        style = MaterialTheme.typography.extraSmall,
                        color = MaterialTheme.colors.darkText,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.spacing.medium)
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_time),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.cyan,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconWithRotate(
                        width = 15.dp,
                        height = 15.dp,
                        painter = painterResource(id = R.drawable.arrow_back),
                        tint = MaterialTheme.colors.cyan,
                    ) {

                    }
                }
            }
        }
    }
}