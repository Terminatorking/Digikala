package ghazimoradi.soheil.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.DigitHelper

@Composable
fun CheckoutProductCard(item: CartItem) {
    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
    ) {

        Image(
            modifier = Modifier.size(75.dp),
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            color = MaterialTheme.colorScheme.darkText,
            text = DigitHelper.digitByLocate(item.count.toString()),
            style = MaterialTheme.typography.extraSmall
        )
    }

    VerticalDivider(
        color = MaterialTheme.colorScheme.gray.copy(0.4f),
        modifier = Modifier
            .height(70.dp)
            .width(1.dp)
    )
}