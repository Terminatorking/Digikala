package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.logoChangeByLanguage
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun AmazingOfferCard(
    bottomImageResId: Int,
    isSuperMarketAmazing: Boolean = false
) {

    Column(
        modifier = Modifier
            .width(160.dp)
            .height(380.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = logoChangeByLanguage(
                enLogo = R.drawable.amazing_en,
                faLogo = if (isSuperMarketAmazing)
                    R.drawable.supermarketamazings
                else R.drawable.amazings
            ),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = bottomImageResId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.h6,
                color = White,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                tint = White,
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = ""
            )
        }
    }
}