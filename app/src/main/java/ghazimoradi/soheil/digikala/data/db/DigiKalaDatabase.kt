package ghazimoradi.soheil.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.data.models.prfile.FavItem
import ghazimoradi.soheil.digikala.utils.Constants.FAVORITE_LIST_TABLE

@Database(entities = [CartItem::class, FavItem::class], version = 2, exportSchema = false)
abstract class DigiKalaDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    abstract fun FavoriteListDao(): FavoriteListDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `${FAVORITE_LIST_TABLE}` " +
                            "(`id` TEXT NOT NULL, " +
                            "`discountPercent` INTEGER NOT NULL, " +
                            "`image` TEXT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`price` INTEGER NOT NULL, " +
                            "`seller` TEXT NOT NULL, " +
                            "`star` REAL NOT NULL, " +
                            "PRIMARY KEY(`id`))"
                )
            }
        }
    }
}