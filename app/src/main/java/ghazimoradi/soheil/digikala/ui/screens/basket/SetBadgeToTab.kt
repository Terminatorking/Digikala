package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun SetBadgeToTab(
    selectedTabIndex: Int,
    index: Int,
    cartCounter: Int
) {
    var color = MaterialTheme.colorScheme.gray

    if (selectedTabIndex == index) {
        color = MaterialTheme.colorScheme.DigiKalaRed
    }

    Card(
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = CircleShape,
    ) {

        Text(
            text = digitByLocateAndSeparator(cartCounter.toString()),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.mainBg,
            modifier = Modifier
                // .background(color = color)
                .padding(horizontal = MaterialTheme.spacing.semiSmall)
        )
    }
}