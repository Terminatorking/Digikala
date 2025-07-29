package ghazimoradi.soheil.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper

@Composable
fun CheckoutProductCard(
    item: CartItem
) {
    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .size(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(MaterialTheme.roundedShape.extraSmall),
            contentAlignment = Alignment.BottomEnd
        ){
            Text(
                text = DigitHelper.digitByLocate(item.count.toString()),
                style = MaterialTheme.typography.extraSmall
            )
        }
    }

    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(70.dp)
            .alpha(0.4f)
            .width(1.dp)

    )
}