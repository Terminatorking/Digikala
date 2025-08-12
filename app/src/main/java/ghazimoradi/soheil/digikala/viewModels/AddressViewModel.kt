package ghazimoradi.soheil.digikala.viewModels

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import  ghazimoradi.soheil.digikala.data.models.address.AddAddressRequest
import  ghazimoradi.soheil.digikala.data.models.address.UserAddress
import  ghazimoradi.soheil.digikala.data.remote.NetworkResult
import  ghazimoradi.soheil.digikala.repositories.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import ghazimoradi.soheil.digikala.utils.Constants.USER_TOKEN

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressRepository
) : RemoteViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())

    val addNewAddressResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

    fun setNewAddress(address: AddAddressRequest) {
        viewModelScope.launch {
            addNewAddressResponse.emit(repository.setNewAddress(address))
        }
    }

    override fun getAllDataFromServer() = getUserAddressList(USER_TOKEN)

}