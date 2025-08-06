package ghazimoradi.soheil.digikala.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ghazimoradi.soheil.digikala.data.db.DigiKalaDatabase
import ghazimoradi.soheil.digikala.data.db.DigiKalaDatabase.Companion.MIGRATION_1_2
import ghazimoradi.soheil.digikala.util.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DigiKalaDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(MIGRATION_1_2)
     .build()
}