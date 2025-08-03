package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.product_detail.ProductDetail
import ghazimoradi.soheil.digikala.ui.theme.DigikalaLightGreen
import ghazimoradi.soheil.digikala.ui.theme.Gold
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.grayAlpha
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocate
import java.util.Locale

@Composable
fun ProductDetailHeaderSection(item: ProductDetail) {
    Column {
        Text(
            text = "${stringResource(id = R.string.category)} / ${item.category}",
            color = MaterialTheme.colors.cyan,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )

        Text(
            text = item.name.toString(),
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
            maxLines = 2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colors.Gold
            )
            Text(
                text = digitByLocate(item.star.toString()),
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = digitByLocate("(${item.starCount})"),
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )

            Text(
                text = digitByLocate("${item.commentCount} ${stringResource(R.string.user_comments)}"),
                color = MaterialTheme.colors.cyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )
            Text(
                text = digitByLocate("${item.viewCount} ${stringResource(R.string.view)}"),
                color = MaterialTheme.colors.cyan,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small,
                )
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.DigikalaLightGreen
            )


            val percent = ((item.star?.div(5.0) ?: 0.0) * 100).toInt()
            val users = (percent * (item.starCount?.toDouble() ?: 0.0) / 100).toInt()

            val text = String.format(
                Locale.getDefault(),
                "%d%% (%d نفر) از خریداران این کالا را پیشنهاد کرده اند.",
                percent,
                users
            )

            Text(
                text = digitByLocate(text),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
        }

        HorizontalDivider(
            color = MaterialTheme.colors.gray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )
    }
}