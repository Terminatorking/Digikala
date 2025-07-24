package ghazimoradi.soheil.digikala.data.db

import androidx.room.*
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow
import ghazimoradi.soheil.digikala.util.Constants.SHOPPING_CART_TABLE

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from $SHOPPING_CART_TABLE where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item: CartItem)

    @Query("DELETE FROM $SHOPPING_CART_TABLE where cartStatus=:status")
    fun deleteAllItems(status: CartStatus)

    @Query("update $SHOPPING_CART_TABLE set count=:newCount where itemId=:id")
    suspend fun changeCountCartItem(id: String, newCount: Int)

    @Query("update $SHOPPING_CART_TABLE set cartStatus=:newCartStatus where itemId=:id")
    suspend fun changeStatusCart(id: String, newCartStatus: CartStatus)

    @Query("select total(count) as count from $SHOPPING_CART_TABLE where cartStatus=:status")
    fun getCartItemsCount(status: CartStatus): Flow<Int>

    @Query("select total(count) as count from $SHOPPING_CART_TABLE where itemId = :itemId")
    fun getItemsCountInBasket(itemId: String): Flow<Int>

    @Query("SELECT EXISTS(SELECT * FROM $SHOPPING_CART_TABLE WHERE itemId = :itemId)")
    fun isItemExistInBasket(itemId: String): Flow<Boolean>
}