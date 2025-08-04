package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.screens.basket.DetailRow
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaLightRed
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaLightGreen
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper

@Composable
fun SellerInfoSection(productPrice: Long) {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.gray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.medium,
            )
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Text(
            text = stringResource(id = R.string.seller),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_logo),
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    text = stringResource(id = R.string.digikala),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colorScheme.darkText,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${DigitHelper.digitByLocate("99")}%" +
                                " رضایت خریداران | عملکرد ",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colorScheme.semiDarkText,
                    )
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                )
            }
        }

        Row(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.semiMedium,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                painter = painterResource(id = R.drawable.guarantee),
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.small)
                    .size(25.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.productWarranty),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colorScheme.darkText,
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.biggerSmall)
        )
        {
            Column(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.in_stock),
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp),
                    tint = MaterialTheme.colorScheme.cyan
                )

                VerticalDivider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp),
                    color = MaterialTheme.colorScheme.gray.copy(0.6f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colorScheme.cyan,
                )

                VerticalDivider(
                    Modifier
                        .width(2.dp)
                        .height(16.dp),
                    color = MaterialTheme.colorScheme.gray.copy(0.6f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .padding(1.dp),
                    tint = MaterialTheme.colorScheme.cyan,
                )
            }

            Column(Modifier.padding(horizontal = MaterialTheme.spacing.biggerMedium)) {

                Text(
                    text = stringResource(id = R.string.in_digi_stock),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colorScheme.darkText,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                )

                DetailRow(
                    painterResource(id = R.drawable.k1),
                    stringResource(id = R.string.digikala_send),
                    color = MaterialTheme.colorScheme.DigiKalaLightRed,
                    fontStyle = MaterialTheme.typography.extraSmall
                )

                DetailRow(
                    painterResource(id = R.drawable.k2),
                    stringResource(id = R.string.fast_send),
                    color = MaterialTheme.colorScheme.DigiKalaLightGreen,
                    fontStyle = MaterialTheme.typography.extraSmall
                )
            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.gray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = MaterialTheme.spacing.small)
        )

        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.digiclub_get_score),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colorScheme.darkText,
                )
            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.gray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = MaterialTheme.spacing.small)
        )

        Row(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                painter = painterResource(id = R.drawable.info),
                modifier = Modifier
                    .size(20.dp)
                    .clip(MaterialTheme.roundedShape.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small),
                    text = "${stringResource(id = R.string.manufacturer_price)} ${
                        DigitHelper.digitByLocateAndSeparator(
                            productPrice.toString()
                        )
                    } ${
                        stringResource(
                            id = R.string.toman
                        )
                    }",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colorScheme.darkText,
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Row(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.better_price_suggestion),
                style = MaterialTheme.typography.extraSmall,
                color = MaterialTheme.colorScheme.darkText,
            )

            Icon(
                tint = MaterialTheme.colorScheme.icon,
                painter = painterResource(id = R.drawable.mark),
                modifier = Modifier.size(25.dp),
                contentDescription = ""
            )
        }
    }
}