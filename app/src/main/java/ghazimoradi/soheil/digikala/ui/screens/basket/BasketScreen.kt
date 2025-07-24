package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.Red
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController = navController)
}

@Composable
fun Basket(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val tabTitles = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainBg)
    ) {
        TabRow(
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Red)
                )
            },
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(MaterialTheme.colors.searchBarBg),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colors.DigiKalaRed,
                    unselectedContentColor =  MaterialTheme.colors.gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ShoppingCart(navController)
            1 -> NextShoppingList(navController)
        }
    }
}