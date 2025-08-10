package ghazimoradi.soheil.digikala.data.remote.apiInterfaces

import ghazimoradi.soheil.digikala.data.models.purchase.PaymentRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentResponse
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationResponse
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