package ghazimoradi.soheil.digikala.data.db

import androidx.room.*
import ghazimoradi.soheil.digikala.data.model.prfile.FavItem
import kotlinx.coroutines.flow.Flow
import ghazimoradi.soheil.digikala.util.Constants.FAVORITE_LIST_TABLE

@Dao
interface FavoriteListDao {
    @Query("SELECT * FROM $FAVORITE_LIST_TABLE")
    fun getAllFavoriteItems() : Flow<List<FavItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(favItem: FavItem)

    @Delete
    suspend fun removeFavoriteItem(favItem: FavItem)

    @Query("DELETE FROM $FAVORITE_LIST_TABLE")
    suspend fun clearFavoriteList()

    @Query("SELECT EXISTS(SELECT * FROM $FAVORITE_LIST_TABLE WHERE id = :itemId)")
    fun isFavoriteItemExist(itemId: String): Flow<Boolean>
}