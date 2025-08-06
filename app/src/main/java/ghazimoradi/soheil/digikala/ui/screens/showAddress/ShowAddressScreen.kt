package ghazimoradi.soheil.digikala.ui.screens.showAddress

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.model.address.UserAddress
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.Loading
import ghazimoradi.soheil.digikala.ui.components.getScreenHeight
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.util.Constants
import ghazimoradi.soheil.digikala.viewmodel.AddressViewModel
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel

@Composable
fun ShowAddressScreen(
    navController: NavHostController,
    isFromBasket: Int,
    viewModel: AddressViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()
) {

    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.getUserAddressList(Constants.USER_TOKEN)
    }

    val addressListResult by viewModel.userAddressList.collectAsState()

    when (addressListResult) {
        is NetworkResult.Success -> {
            addressList = addressListResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "addressListSection error : ${addressListResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddAddress.route)
                },
                containerColor = MaterialTheme.colorScheme.DigiKalaRed
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Log.i("padding", padding.toString())
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.mainBg)
        ) {
            ShowAddressHeader(navController)

            if (loading) {
                Loading(getScreenHeight())
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    itemsIndexed(addressList) { index, item ->
                        AddressCard(item, isFromBasket, index, dataStore, navController)
                    }
                }
            }
        }
    }
}