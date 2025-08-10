package ghazimoradi.soheil.digikala.di.apiInterfaceModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.ProductDetailApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProductDetailApiService(retrofit: Retrofit) : ProductDetailApiInterface =
        retrofit.create(ProductDetailApiInterface::class.java)
}