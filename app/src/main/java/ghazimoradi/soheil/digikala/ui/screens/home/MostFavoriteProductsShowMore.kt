package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.IconWithRotate

@Composable
fun MostFavoriteProductsShowMore() {
    Column(
        modifier = Modifier
            .size(180.dp, 320.dp)
            .padding(
                end = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.semiSmall,
                top = MaterialTheme.spacing.semiLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        IconWithRotate(
            painter = painterResource(id = R.drawable.show_more),
            tint = MaterialTheme.colors.DarkCyan,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
        )
    }
}