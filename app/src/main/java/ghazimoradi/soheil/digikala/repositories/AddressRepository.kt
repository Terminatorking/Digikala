package ghazimoradi.soheil.digikala.repositories

import ghazimoradi.soheil.digikala.data.models.address.AddAddressRequest
import ghazimoradi.soheil.digikala.data.models.address.UserAddress
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.AddressApiInterface
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