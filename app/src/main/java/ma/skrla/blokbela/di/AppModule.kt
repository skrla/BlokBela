package ma.skrla.blokbela.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ma.skrla.blokbela.data.local.BelaDao
import ma.skrla.blokbela.data.local.BelaDatabase
import ma.skrla.blokbela.repository.BelaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): BelaDatabase {
        return Room.databaseBuilder(
            app,
            BelaDatabase::class.java,
            "bela_data"
        ).build()
    }


    @Provides
    @Singleton
    fun provideDao(belaDatabase: BelaDatabase) : BelaDao {
        return belaDatabase.belaDao
    }

    @Provides
    fun provideBelaRepository(belaDao: BelaDao): BelaRepository {
        return BelaRepository(belaDao)
    }
}