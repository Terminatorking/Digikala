package ghazimoradi.soheil.digikala.ui.screens.user.userFavoriteProducts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.font_standard
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFavoriteProductTopAppBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "arrow back",
                tint = MaterialTheme.colorScheme.icon,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.my_fav_list),
                color = MaterialTheme.colorScheme.darkText,
                fontFamily = font_standard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.small)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.mainBg),
        modifier = Modifier.height(70.dp)
    )
}