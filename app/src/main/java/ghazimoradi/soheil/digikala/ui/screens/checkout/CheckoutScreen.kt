package ghazimoradi.soheil.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.BuyProcessContinue
import ghazimoradi.soheil.digikala.ui.components.CartPriceDetailSection
import ghazimoradi.soheil.digikala.ui.components.OurLoading
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel
import ghazimoradi.soheil.digikala.viewmodel.CheckoutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val cartDetail by basketViewModel.cartDetail.collectAsState()
    val currentCartItems by basketViewModel.ourCartItems.collectAsState()

    var shippingCost by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }
    var addressPhone by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        if (address.isNotBlank())
            checkoutViewModel.getShippingCost(address)
        else
            checkoutViewModel.getShippingCost("")
    }

    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen error : ${shippingCostResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            it != ModalBottomSheetValue.HalfExpanded
        },
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.mainBg),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn {
                item {
                    CheckoutTopBarSection(navController)
                }
                item {
                    CartAddressSection(navController) { addressList ->
                        if (addressList.isNotEmpty()) {
                            address = addressList[0].address
                            addressName = addressList[0].name
                            addressPhone = addressList[0].phone
                        }
                    }
                }
                item {
                    CartItemReviewSection(cartDetail, currentCartItems) {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible) {
                                modalSheetState.hide()
                            } else {
                                modalSheetState.show()
                            }
                        }
                    }
                }
                item {
                    CartInfoSection()
                }
                item {
                    CartPriceDetailSection(cartDetail, shippingCost)
                }
            }

            if (loading) {
                OurLoading(height = 65.dp)
            } else {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(
                            start = MaterialTheme.spacing.biggerSmall,
                            end = MaterialTheme.spacing.biggerSmall,
                        )
                ) {
                    BuyProcessContinue(cartDetail.payablePrice, shippingCost) {

                    }
                }
            }
        }
    }
}