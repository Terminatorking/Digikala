package ghazimoradi.soheil.digikala.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.model.checkout.ConfirmPurchase
import ghazimoradi.soheil.digikala.data.model.checkout.OrderDetail
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.repository.CheckoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val repository: CheckoutRepository
) : RemoteViewModel() {

    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())

    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    val purchaseResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getShippingCost(address : String){
        viewModelScope.launch(Dispatchers.IO) {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }

    fun addNewOrder(cartOrderDetail: OrderDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                orderResponse.emit(repository.setNewOrder(cartOrderDetail))
            }
        }
    }

    fun confirmPurchase(confirmPurchase: ConfirmPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                purchaseResponse.emit(repository.confirmPurchase(confirmPurchase))
            }
        }
    }
}