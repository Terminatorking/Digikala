package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.basket.CartDetails
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.utils.DigitHelper.digitByLocateAndSeparator

@Composable
fun CartPriceDetailSection(
    item: CartDetails,
    shippingCost: Int = 0
) {
    var title = stringResource(id = R.string.basket_summary)

    if (shippingCost > 0) {
        title = stringResource(id = R.string.cost_details)
    }

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            end = MaterialTheme.spacing.medium,
            top = MaterialTheme.spacing.medium,
            bottom = 120.dp
        )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colorScheme.darkText
            )

            Text(
                text = "${digitByLocateAndSeparator(item.totalCount.toString())} ${
                    stringResource(
                        R.string.goods
                    )
                }",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.darkText
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

        PriceRow(
            stringResource(id = R.string.goods_price),
            digitByLocateAndSeparator(item.totalPrice.toString())
        )

        val discountPercent = (1 - item.payablePrice.toDouble() / item.totalPrice.toDouble()) * 100

        PriceRow(
            stringResource(id = R.string.goods_discount),
            digitByLocateAndSeparator(item.totalDiscount.toString()),
            discountPercent.toInt()
        )

        PriceRow(
            stringResource(id = R.string.goods_total_price),
            digitByLocateAndSeparator(item.payablePrice.toString())
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (shippingCost > 0) {
            HorizontalDivider(
                Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.medium,
                        horizontal = MaterialTheme.spacing.small
                    )
                    .alpha(0.6f),
                color = MaterialTheme.colorScheme.gray
            )

            PriceRow(
                stringResource(id = R.string.delivery_cost),
                digitByLocateAndSeparator(shippingCost.toString())
            )

            FirstDotTextRow(stringResource(id = R.string.shipping_cost_last_alert))

            PriceRow(
                stringResource(id = R.string.final_price),
                digitByLocateAndSeparator((item.payablePrice + shippingCost).toString())
            )

        } else {
            FirstDotTextRow(stringResource(id = R.string.shipping_cost_alert))
        }

        HorizontalDivider(
            Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.small
                )
                .alpha(0.6f),
            color = MaterialTheme.colorScheme.gray
        )

        DigiClubScore(item.payablePrice)
    }
}

@Composable
fun FirstDotTextRow(text: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.dot_bullet),
            color = MaterialTheme.colorScheme.darkText,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun DigiClubScore(payedPrice: Long) {

    val score = payedPrice / 100_000

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.darkText,
            )
        }

        Text(
            text = "${digitByLocateAndSeparator(score.toString())} " +
                    stringResource(id = R.string.score),

            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.darkText,
        )
    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))

    Text(
        text = stringResource(R.string.digiclub_description),
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colorScheme.darkText,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.biggerSmall)
    )
}

@Composable
private fun PriceRow(
    title: String,
    price: String,
    discount: Int = 0,
) {
    var color = MaterialTheme.colorScheme.darkText
    var ourPrice = price

    if (discount > 0) {
        color = MaterialTheme.colorScheme.DigiKalaRed
        ourPrice = "(${digitByLocateAndSeparator(discount.toString())}%) $price"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
        )

        Row {
            Text(
                text = ourPrice,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )

            Icon(
                painter = logoChangeByLanguage(
                    enLogo = R.drawable.dollar,
                    faLogo = R.drawable.toman
                ),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.icon,
                modifier = Modifier
                    .size(24.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
    }
}