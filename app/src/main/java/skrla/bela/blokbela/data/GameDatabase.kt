package skrla.bela.blokbela.data

import androidx.room.Database
import androidx.room.RoomDatabase
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.ScoreTwoPlayers

@Database(entities = [Round::class, ScoreTwoPlayers::class], version = 1, exportSchema = false)
abstract class   GameDatabase : RoomDatabase() {

    abstract val gameDao: GameDao

}