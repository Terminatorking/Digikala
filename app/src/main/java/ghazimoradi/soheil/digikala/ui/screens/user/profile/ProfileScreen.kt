package ghazimoradi.soheil.digikala.ui.screens.user.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.models.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.screens.auth.login.LoginScreen
import ghazimoradi.soheil.digikala.ui.screens.auth.register.RegisterScreen
import ghazimoradi.soheil.digikala.viewModels.DataStoreViewModel
import ghazimoradi.soheil.digikala.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val userToken = dataStore.getUserToken()

    if (!userToken.isNullOrBlank() && userToken != "null") {
        ProfileScreenContent(navController, getUserOrders(profileViewModel = profileViewModel))
    } else {
        when (profileViewModel.screenState) {
            ProfileScreenState.LOGIN_STATE -> {
                LoginScreen(navController = navController)
            }

            ProfileScreenState.PROFILE_STATE -> {
                ProfileScreenContent(
                    navController,
                    getUserOrders(profileViewModel = profileViewModel)
                )
            }

            ProfileScreenState.REGISTER_STATE -> {
                RegisterScreen(navController = navController)
            }
        }
    }
}

@Composable
private fun getUserOrders(profileViewModel: ProfileViewModel): List<OrderFullDetail> {
    LaunchedEffect(true) {
        profileViewModel.getAllDataFromServer()
    }

    var orderItemsList by remember {
        mutableStateOf<List<OrderFullDetail>>(emptyList())
    }

    val orderItemsResult by profileViewModel.orderItems.collectAsState()

    when (orderItemsResult) {
        is NetworkResult.Success -> {
            orderItemsList = orderItemsResult.data ?: emptyList()
        }

        is NetworkResult.Error -> {
            Log.e("3636", "OrderItemsResultSection error : ${orderItemsResult.message}")
        }

        is NetworkResult.Loading -> {}
    }
    return orderItemsList
}