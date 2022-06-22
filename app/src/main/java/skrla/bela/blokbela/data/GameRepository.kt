package skrla.bela.blokbela.data


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score

interface GameRepository {

    fun getScore(): Flow<List<Score>>

    fun getCurrentRound(): LiveData<Round>

    suspend fun insertScore(score: Score)

    suspend fun insertRound(round: Round)
}