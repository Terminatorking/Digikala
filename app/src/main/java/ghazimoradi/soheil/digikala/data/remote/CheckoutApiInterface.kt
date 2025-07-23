package ghazimoradi.soheil.digikala.data.remote

import ghazimoradi.soheil.digikala.data.model.ResponseResult
import ghazimoradi.soheil.digikala.data.model.checkout.ConfirmPurchase
import ghazimoradi.soheil.digikala.data.model.checkout.OrderDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckoutApiInterface {

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(
        @Query("address") address: String
    ): Response<ResponseResult<Int>>

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(
        @Body orderRequest: OrderDetail
    ): Response<ResponseResult<String>>

    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(
        @Body confirmPurchase: ConfirmPurchase
    ): Response<ResponseResult<String>>
}