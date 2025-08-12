package ghazimoradi.soheil.digikala.ui.screens.user.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.digiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.DigitHelper.digitByLocateAndSeparator

@Composable
fun ProfileOrdersItem(
    text: String,
    count: Int,
    painter: Painter
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            ){

                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )

                Card(
                    Modifier.align(Alignment.BottomEnd),
                    shape = MaterialTheme.roundedShape.extraSmall,
                    border = BorderStroke(1.dp, White)
                ){
                    Text(
                        text = digitByLocateAndSeparator(count.toString()),
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.digiKalaRed)
                            .height(20.dp)
                            .padding(horizontal = MaterialTheme.spacing.semiSmall),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                }
            }

            Text(
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.darkText,
                text = text
            )
        }

        VerticalDivider(
            modifier = Modifier
                .width(1.dp)
                .height(90.dp)
                .alpha(0.4f),
            color = MaterialTheme.colorScheme.darkText,
        )
    }
}