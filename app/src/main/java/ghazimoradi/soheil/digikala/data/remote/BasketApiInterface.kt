package ghazimoradi.soheil.digikala.data.remote

import ghazimoradi.soheil.digikala.data.model.ResponseResult
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET

interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<StoreProduct>>>
}