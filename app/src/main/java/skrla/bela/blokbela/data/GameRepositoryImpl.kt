package skrla.bela.blokbela.data

import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score

class GameRepositoryImpl(
    private val gameDao: GameDao
) : GameRepository {
    override fun getScore(): Flow<List<Score>> {
        return gameDao.getScore()
    }

    override suspend fun insertScore(score: Score) {
        gameDao.insertScore(score)
    }

    override suspend fun insertRound(round: Round) {
        gameDao.insertRound(round)
    }
}