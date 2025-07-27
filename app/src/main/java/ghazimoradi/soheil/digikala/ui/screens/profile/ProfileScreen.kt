package ghazimoradi.soheil.digikala.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.CenterBannerItem
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel
import ghazimoradi.soheil.digikala.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    profileViewModel.getAllDataFromServer()

    var orderItemsList by remember {
        mutableStateOf<List<OrderFullDetail>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val orderItemsResult by profileViewModel.orderItems.collectAsState()

    when (orderItemsResult) {
        is NetworkResult.Success -> {
            orderItemsList = orderItemsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "OrderItemsResultSection error : ${orderItemsResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Profile(navController = navController, orderItemsList)
}

@Composable
fun Profile(
    navController: NavHostController,
    orders: List<OrderFullDetail>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainBg)
            .padding(bottom = 60.dp),
    ) {
        item {
            ProfileTopBarSection(navController)
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
                painter = painterResource( R.drawable.digiclub2),
                navController
            )
        }
    }
}