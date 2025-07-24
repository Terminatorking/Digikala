package ghazimoradi.soheil.digikala.repository

import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.BasketApiInterface
import javax.inject.Inject

class BasketRepository @Inject constructor(
    val api: BasketApiInterface
) : BaseApiResponse() {

}