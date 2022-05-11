package skrla.bela.blokbela.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.ScoreTwoPlayers

@Dao
interface GameDao {

    @Query("SELECT * FROM scoreTwoPlayers")
    fun getScore(): Flow<List<ScoreTwoPlayers>>

    @Insert
    suspend fun insertScore(scoreTwoPlayers: ScoreTwoPlayers)
}