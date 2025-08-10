package ghazimoradi.soheil.digikala.di.dataBaseModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.db.DigiKalaDatabase
import ghazimoradi.soheil.digikala.data.db.FavoriteListDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDaoModule {

    @Provides
    @Singleton
    fun provideFavoriteListDao(
        database: DigiKalaDatabase
    ): FavoriteListDao = database.FavoriteListDao()
}