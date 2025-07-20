package ghazimoradi.soheil.digikala.data.remote

import ghazimoradi.soheil.digikala.data.model.purchase.PaymentRequest
import ghazimoradi.soheil.digikala.data.model.purchase.PaymentResponse
import ghazimoradi.soheil.digikala.data.model.purchase.PaymentVerificationRequest
import ghazimoradi.soheil.digikala.data.model.purchase.PaymentVerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PurchaseApiInterface {

    @POST("request.json")
    suspend fun startPurchase(
        @Body paymentRequest: PaymentRequest
    ) : Response<PaymentResponse>


    @POST("verify.json")
    suspend fun verifyPurchase(
        @Body paymentVerificationRequest: PaymentVerificationRequest
    ) : Response<PaymentVerificationResponse>

}