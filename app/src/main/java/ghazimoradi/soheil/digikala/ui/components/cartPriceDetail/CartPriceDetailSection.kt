package ghazimoradi.soheil.digikala.ui.components.cartPriceDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.basket.CartDetails
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.spacing
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