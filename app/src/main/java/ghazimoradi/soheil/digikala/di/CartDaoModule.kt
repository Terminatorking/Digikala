package ghazimoradi.soheil.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.db.CartDao
import ghazimoradi.soheil.digikala.data.db.DigikalaDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDaoModule {

    @Provides
    @Singleton
    fun provideCartDao(
        database: DigikalaDatabase
    ): CartDao = database.cartDao()
}