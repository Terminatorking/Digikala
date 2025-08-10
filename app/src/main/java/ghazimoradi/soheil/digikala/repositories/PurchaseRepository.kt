package ghazimoradi.soheil.digikala.repositories

import android.util.Log
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentResponse
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationResponse
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.PurchaseApiInterface
import retrofit2.HttpException
import javax.inject.Inject

class PurchaseRepository @Inject constructor(
    private val api: PurchaseApiInterface
) {

    suspend fun startPurchase(
        paymentRequest: PaymentRequest
    ): PaymentResponse? {

        return try {
            val response = api.startPurchase(paymentRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            Log.e("PurchaseRepository", "Failed to start purchase", e)
            throw e
        }
    }

    suspend fun verifyPurchase(
        paymentVerificationRequest: PaymentVerificationRequest
    ): PaymentVerificationResponse? {
        return try {
            val response = api.verifyPurchase(paymentVerificationRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            Log.e("PurchaseRepository", "Failed to verify purchase", e)
            throw e
        }
    }
}