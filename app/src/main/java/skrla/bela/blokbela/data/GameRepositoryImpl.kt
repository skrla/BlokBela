package skrla.bela.blokbela.data

import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.ScoreTwoPlayers

class GameRepositoryImpl(
    private val gameDao: GameDao
) : GameRepository {
    override fun getScore(): Flow<List<ScoreTwoPlayers>> {
        return gameDao.getScore()
    }

    override suspend fun insertScore(scoreTwoPlayers: ScoreTwoPlayers) {
        gameDao.insertScore(scoreTwoPlayers)
    }
}