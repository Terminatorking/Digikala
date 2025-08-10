package ghazimoradi.soheil.digikala.data.remote.apiInterfaces

import ghazimoradi.soheil.digikala.data.models.ResponseResult
import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET

interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<StoreProduct>>>
}