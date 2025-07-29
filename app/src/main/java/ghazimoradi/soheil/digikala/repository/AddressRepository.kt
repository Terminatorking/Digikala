package ghazimoradi.soheil.digikala.repository

import ghazimoradi.soheil.digikala.data.model.address.AddAddressRequest
import ghazimoradi.soheil.digikala.data.model.address.UserAddress
import ghazimoradi.soheil.digikala.data.remote.AddressApiInterface
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import javax.inject.Inject

class AddressRepository @Inject constructor(
    private val api: AddressApiInterface
) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
          api.getUserAddressList(token)
        }

    suspend fun setNewAddress(address: AddAddressRequest): NetworkResult<String> =
        safeApiCall {
            api.saveUserAddress(address)
        }
}