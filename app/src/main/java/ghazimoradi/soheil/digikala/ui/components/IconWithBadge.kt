package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun IconWithBadge(
    tint: Color,
    cartCounter: Int,
    icon: Painter
) {

    Box(modifier = Modifier.height(28.dp)) {

        Box(
            modifier = Modifier
                .height(28.dp)
                .width(36.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                tint = tint,
                painter = icon,
                contentDescription = "",
                modifier = Modifier
                    .height(24.dp),
            )
        }

        Box(
            modifier = Modifier.height(28.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Card(
                shape = MaterialTheme.roundedShape.extraSmall,
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = digitByLocateAndSeparator(cartCounter.toString()),
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.DigiKalaRed)
                        .height(16.dp)
                        .padding(horizontal = MaterialTheme.spacing.semiSmall),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.extraSmall,
                    fontWeight = FontWeight.Bold,
                    color = White,
                )
            }
        }
    }
}