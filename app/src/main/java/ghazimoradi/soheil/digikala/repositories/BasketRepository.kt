package ghazimoradi.soheil.digikala.repositories

import ghazimoradi.soheil.digikala.data.db.CartDao
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.data.models.basket.CartStatus
import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.BasketApiInterface
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItems(CartStatus.NEXT_CART)

    val currentCartItemsCount = dao.getCartItemsCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCartItemsCount(CartStatus.NEXT_CART)

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeFromCart(cart)
    }

    fun deleteAllItems() {
        dao.deleteAllItems(CartStatus.CURRENT_CART)
    }

    suspend fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        dao.changeStatusCart(id, newStatus)
    }

    suspend fun changeCartItemCount(id: String, newCount: Int) {
        dao.changeCountCartItem(id, newCount)
    }

    fun getItemsCountInBasket(itemId: String): Flow<Int> =
        dao.getItemsCountInBasket(itemId)

    fun isItemExistInBasket(itemId: String): Flow<Boolean> =
        dao.isItemExistInBasket(itemId)
}