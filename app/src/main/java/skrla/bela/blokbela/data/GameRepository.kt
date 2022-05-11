package skrla.bela.blokbela.data


import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.ScoreTwoPlayers

interface GameRepository {

    fun getScore(): Flow<List<ScoreTwoPlayers>>

    suspend fun insertScore(scoreTwoPlayers: ScoreTwoPlayers)
}