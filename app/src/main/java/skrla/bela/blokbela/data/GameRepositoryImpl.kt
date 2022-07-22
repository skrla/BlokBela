package skrla.bela.blokbela.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.data.model.Team
import skrla.bela.blokbela.data.model.relations.TeamWithPlayer

class GameRepositoryImpl(
    private val gameDao: GameDao
) : GameRepository {
    override fun getScore(): Flow<List<Score>> {
        return gameDao.getScore()
    }

    override fun getPlayers(): Flow<List<Player>> {
        return gameDao.getPlayers()
    }

    override fun getTeamWithPlayers(): Flow<List<TeamWithPlayer>> {
        return gameDao.getTeamWithPlayer()
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

    override suspend fun insertPlayer(player: Player) {
        gameDao.insertPlayer(player)
    }

    override suspend fun insertTeam(team: Team) {
        gameDao.insertTeam(team)
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