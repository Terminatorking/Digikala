package ghazimoradi.soheil.digikala.ui.screens.user.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.ui.components.CenterBannerItem
import ghazimoradi.soheil.digikala.ui.components.TopBarSection
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun ProfileScreenContent(
    navController: NavHostController,
    orders: List<OrderFullDetail>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg)
            .padding(bottom = 60.dp),
    ) {
        item {
            TopBarSection(navController)
        }
        item {
            ProfileHeaderSection(navController)
        }
        item {
            ProfileMiddleSection(navController)
        }
        item {
            ProfileOrdersSection(navController, orders)
        }
        item {
            CenterBannerItem(
                painter = painterResource(R.drawable.digiclub1),
                navController
            )
        }
        item {
            ProfileMenuSection(navController)
        }
        item {
            CenterBannerItem(
                painter = painterResource(R.drawable.digiclub2),
                navController
            )
        }
    }
}