package ghazimoradi.soheil.digikala.repository

import ghazimoradi.soheil.digikala.data.model.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.data.model.prfile.LoginRequest
import ghazimoradi.soheil.digikala.data.model.prfile.LoginResponse
import ghazimoradi.soheil.digikala.data.model.prfile.SetUserNameRequest
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    val api: ProfileApiInterface
): BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }

    suspend fun setUserName(newUserName: SetUserNameRequest): NetworkResult<String> =
        safeApiCall {
            api.setUserName(newUserName)
        }

    suspend fun getUserOrders(token: String): NetworkResult<List<OrderFullDetail>>  =
        safeApiCall {
            api.getUserOrders(token)
        }
}