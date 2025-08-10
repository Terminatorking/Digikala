package ghazimoradi.soheil.digikala.ui.screens.user.userOrders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.font_standard
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayoutTopAppBar(navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.searchBarBg),
        title = {
            Text(
                text = stringResource(id = R.string.my_orders),
                color = MaterialTheme.colorScheme.darkText,
                fontFamily = font_standard,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = MaterialTheme.spacing.biggerSmall),
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                tint = MaterialTheme.colorScheme.icon,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                tint = MaterialTheme.colorScheme.icon,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.small)
                    .clickable {}
            )
        }
    )
}