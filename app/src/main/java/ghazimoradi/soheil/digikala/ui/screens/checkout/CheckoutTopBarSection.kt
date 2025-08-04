package ghazimoradi.soheil.digikala.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun CheckoutTopBarSection(
    navController: NavHostController,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.Close,
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            contentDescription = "",
            tint = MaterialTheme.colorScheme.icon
        )

        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.address_and_time),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText,
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(MaterialTheme.colorScheme.searchBarBg)
    )
}