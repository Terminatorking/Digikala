package ghazimoradi.soheil.digikala.data.remote.apiInterfaces

import ghazimoradi.soheil.digikala.data.models.ResponseResult
import ghazimoradi.soheil.digikala.data.models.address.AddAddressRequest
import ghazimoradi.soheil.digikala.data.models.address.UserAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String
    ): Response<ResponseResult<List<UserAddress>>>

    @POST("v1/saveUserAddress")
    suspend fun saveUserAddress(
        @Body address: AddAddressRequest
    ): Response<ResponseResult<String>>
}