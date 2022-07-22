package skrla.bela.blokbela.data

import androidx.room.Database
import androidx.room.RoomDatabase
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.data.model.Team

@Database(entities =
    [Round::class, Team::class,
    Player::class, Score::class],
    version = 1, exportSchema = false)
abstract class   GameDatabase : RoomDatabase() {

    abstract val gameDao: GameDao
}