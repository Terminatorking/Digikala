package ghazimoradi.soheil.digikala.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.grayCategory
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.roundedShape

@Composable
fun CartInfoSection() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colors.grayCategory),
        shape = MaterialTheme.roundedShape.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.small
            ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .padding(horizontal = MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "",
                    modifier = Modifier.size(26.dp),
                    tint = Color.DarkGray
                )
            }

            Column(
                modifier = Modifier.weight(0.9f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.factor_info),
                    textAlign = TextAlign.Start,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                )
            }
        }
    }
}