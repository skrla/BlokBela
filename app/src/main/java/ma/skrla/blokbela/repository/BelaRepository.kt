package ma.skrla.blokbela.repository

import kotlinx.coroutines.flow.Flow
import ma.skrla.blokbela.data.local.BelaDao
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity
import ma.skrla.blokbela.data.local.model.TeamEntity
import ma.skrla.blokbela.data.local.model.relations.PlayerEntityWithScoreEntity
import ma.skrla.blokbela.data.local.model.relations.TeamEntityWithPlayerEntityAndScoreEntity


class BelaRepository(private val belaDao: BelaDao) {

    fun getPlayerWithScore() : Flow<List<PlayerEntityWithScoreEntity>> {
        return belaDao.getPlayerWithScore()
    }

    fun getTeamWithPlayerAndScore() : Flow<List<TeamEntityWithPlayerEntityAndScoreEntity>> {
        return belaDao.getTeamWithPlayerAndScore()
    }

    fun getPlayers(): Flow<List<PlayerEntity>> {
        return belaDao.getPlayers()
    }

    fun getScore(): Flow<List<ScoreEntity>> {
        return belaDao.getScore()
    }

    suspend fun insertTeam(team: TeamEntity) {
        belaDao.insertTeam(team)
    }

    suspend fun insertPlayer(player: PlayerEntity) {
        belaDao.insertPlayer(player)
    }

    suspend fun insertScore(score: ScoreEntity) {
        belaDao.insertScore(score)
    }
}