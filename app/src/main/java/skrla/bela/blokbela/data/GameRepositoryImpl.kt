package skrla.bela.blokbela.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score

class GameRepositoryImpl(
    private val gameDao: GameDao
) : GameRepository {
    override fun getScore(): Flow<List<Score>> {
        return gameDao.getScore()
    }

    override fun getCurrentRound(): LiveData<Round> {
        return gameDao.getCurrentRound()
    }

    override suspend fun insertScore(score: Score) {
        gameDao.insertScore(score)
    }

    override suspend fun insertRound(round: Round) {
        gameDao.insertRound(round)
    }

    override suspend fun deletePlayers() {
        gameDao.deletePlayers()
    }

    override suspend fun deleteTeams() {
        gameDao.deleteTeams()
    }

    override suspend fun deleteScores() {
        gameDao.deleteScores()
    }

    override suspend fun deleteRounds() {
        gameDao.deleteRounds()
    }
}