package skrla.bela.blokbela.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import skrla.bela.blokbela.data.GameDatabase
import skrla.bela.blokbela.data.GameRepository
import skrla.bela.blokbela.data.GameRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): GameDatabase {
        return Room.databaseBuilder(
            app,
            GameDatabase::class.java,
            "game_data"
        )
            .build()

    }

    @Provides
    @Singleton
    fun provideGameRepository(gameDatabase: GameDatabase): GameRepository {
        return GameRepositoryImpl(gameDatabase.gameDao)
    }

}