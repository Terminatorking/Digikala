package ghazimoradi.soheil.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.remote.PurchaseApiInterface
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PurchaseApiInterfaceModule {

    @Singleton
    @Provides
    fun providePurchaseApiService(@Named("purchaseUrl") retrofit: Retrofit) : PurchaseApiInterface =
        retrofit.create(PurchaseApiInterface::class.java)
}