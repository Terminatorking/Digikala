package ghazimoradi.soheil.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.IconWithBadge
import ghazimoradi.soheil.digikala.ui.theme.bottomBar
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.selectedBottomBar
import ghazimoradi.soheil.digikala.ui.theme.unSelectedBottomBar
import ghazimoradi.soheil.digikala.utils.Constants.USER_LANGUAGE
import ghazimoradi.soheil.digikala.utils.LocaleUtils
import ghazimoradi.soheil.digikala.viewModels.BasketViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    viewModel: BasketViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    val items = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(R.drawable.home_fill),
            deSelectedIcon = painterResource(R.drawable.home_outline),
        ),
        BottomNavItem(
            name = stringResource(R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(R.drawable.category_fill),
            deSelectedIcon = painterResource(R.drawable.category_outline),
        ),
        BottomNavItem(
            name = stringResource(R.string.basket),
            route = Screen.Basket.route,
            selectedIcon = painterResource(R.drawable.cart_fill),
            deSelectedIcon = painterResource(R.drawable.cart_outline),
        ),
        BottomNavItem(
            name = stringResource(R.string.my_digikala),
            route = Screen.Profile.route,
            selectedIcon = painterResource(R.drawable.user_fill),
            deSelectedIcon = painterResource(R.drawable.user_outline),
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val route = backStackEntry.value?.destination?.route
    val showBottomBar = route in items.map { bottomNavItem -> bottomNavItem.route }

    if (showBottomBar) {
        NavigationBar(
            modifier = Modifier.height(60.dp),
            containerColor = MaterialTheme.colorScheme.bottomBar,
        ) {
            val cartCounter by viewModel.currentCartItemsCount.collectAsState(0)
            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.selectedBottomBar,
                        selectedTextColor = MaterialTheme.colorScheme.selectedBottomBar,
                        disabledIconColor = MaterialTheme.colorScheme.unSelectedBottomBar,
                        disabledTextColor = MaterialTheme.colorScheme.unSelectedBottomBar
                    ),
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                        cartCounter = cartCounter,
                                        icon = item.selectedIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.selectedIcon,
                                        contentDescription = item.name
                                    )
                                }

                            } else {
                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                        cartCounter = cartCounter,
                                        icon = item.deSelectedIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.deSelectedIcon,
                                        contentDescription = item.name
                                    )
                                }
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}