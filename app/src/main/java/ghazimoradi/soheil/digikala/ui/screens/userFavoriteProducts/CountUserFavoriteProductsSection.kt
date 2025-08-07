package ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper

@Composable
fun CountUserFavoriteProductsSection(itemCount: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.biggerSmall,
                bottom = MaterialTheme.spacing.biggerMedium
            )
    ) {

        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.biggerMedium),
            text = stringResource(id = R.string.fav_products),
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
        )
        Text(
            modifier = Modifier.padding(end = MaterialTheme.spacing.biggerMedium),
            text = "${DigitHelper.digitByLocate(itemCount.toString())} ${stringResource(id = R.string.product)}",
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
        )
    }
}