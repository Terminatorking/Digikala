package ghazimoradi.soheil.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.remote.CommentApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommentApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCommentApiService(retrofit: Retrofit) : CommentApiInterface =
        retrofit.create(CommentApiInterface::class.java)
}