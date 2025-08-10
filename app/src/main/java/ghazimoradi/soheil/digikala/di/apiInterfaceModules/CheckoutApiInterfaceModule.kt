package ghazimoradi.soheil.digikala.di.apiInterfaceModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.CheckoutApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCheckoutApiService(retrofit: Retrofit) : CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)
}