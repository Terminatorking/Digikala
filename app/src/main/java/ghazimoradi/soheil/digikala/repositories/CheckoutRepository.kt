package ghazimoradi.soheil.digikala.repositories

import ghazimoradi.soheil.digikala.data.models.checkout.ConfirmPurchase
import ghazimoradi.soheil.digikala.data.models.checkout.OrderDetail
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.CheckoutApiInterface
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val api: CheckoutApiInterface
) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
           api.getShippingCost(address)
        }

    suspend fun setNewOrder(cartOrderDetail: OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }
}