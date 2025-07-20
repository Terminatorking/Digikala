package ghazimoradi.soheil.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.selectedBottomBar
import ghazimoradi.soheil.digikala.ui.theme.unSelectedBottomBar

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
) {
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
        BottomNavigation(
            modifier = Modifier,
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            items.forEachIndexed { index, bottomNavItem ->
                val selected = bottomNavItem.route == route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick.invoke(bottomNavItem) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = bottomNavItem.selectedIcon,
                                    contentDescription = bottomNavItem.name
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = bottomNavItem.deSelectedIcon,
                                    contentDescription = bottomNavItem.name
                                )
                            }
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = bottomNavItem.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                )
            }
        }
    }
}