package ghazimoradi.soheil.digikala.ui.components.buy

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.extentions.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.utils.DigitHelper.digitByLocateAndSeparator

@Composable
fun BuyProcessContinue(
    price: Long,
    shippingCost: Int = 0,
    onClick: () -> Unit,
) {

    var title = stringResource(id = R.string.goods_total_price)

    if (shippingCost > 0) {
        title = stringResource(id = R.string.final_price)
    }

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.searchBarBg),
        shape = MaterialTheme.roundedShape.extraSmall,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.gray.copy(0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.semiMedium,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.DigiKalaRed),
                shape = MaterialTheme.roundedShape.small
            ) {
                Text(
                    text = stringResource(R.string.purchase_process),
                    color = White,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.biggerMedium,
                            vertical = MaterialTheme.spacing.extraSmall,
                        )
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.darkText,
                    style = MaterialTheme.typography.h6,
                )

                Row {
                    Text(
                        color = MaterialTheme.colorScheme.darkText,
                        text = digitByLocateAndSeparator((price + shippingCost).toString()),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.SemiBold
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