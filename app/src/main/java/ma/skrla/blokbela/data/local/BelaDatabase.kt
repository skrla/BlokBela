package ma.skrla.blokbela.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity
import ma.skrla.blokbela.data.local.model.TeamEntity

@Database(entities = [PlayerEntity::class, TeamEntity::class, ScoreEntity::class], version = 1, exportSchema = false)
abstract class BelaDatabase : RoomDatabase() {
    abstract val belaDao: BelaDao
}