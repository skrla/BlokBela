package skrla.bela.blokbela.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score

@Dao
interface GameDao {

    @Query("SELECT * FROM score")
    fun getScore(): Flow<List<Score>>

    @Insert
    suspend fun insertScore(score: Score)

    @Insert
    suspend fun insertRound(round: Round)
}