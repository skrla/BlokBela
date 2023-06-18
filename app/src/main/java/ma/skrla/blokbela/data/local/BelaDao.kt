package ma.skrla.blokbela.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity
import ma.skrla.blokbela.data.local.model.TeamEntity
import ma.skrla.blokbela.data.local.model.relations.PlayerEntityWithScoreEntity
import ma.skrla.blokbela.data.local.model.relations.TeamEntityWithPlayerEntityAndScoreEntity
import ma.skrla.blokbela.domain.Score

@Dao
interface BelaDao {

    @Transaction
    @Query("SELECT * FROM team")
    fun getTeamWithPlayerAndScore() : Flow<List<TeamEntityWithPlayerEntityAndScoreEntity>>


    @Transaction
    @Query("SELECT * FROM player")
    fun getPlayerWithScore() : Flow<List<PlayerEntityWithScoreEntity>>

    @Transaction
    @Query("SELECT * FROM score")
    fun getScore(): Flow<List<ScoreEntity>>

    @Upsert
    suspend fun insertTeam(team: TeamEntity)

    @Upsert
    suspend fun insertPlayer(player: PlayerEntity)

    @Upsert
    suspend fun insertScore(score: ScoreEntity)

    @Query("DELETE FROM team")
    suspend fun deleteTeams()

    @Query("DELETE FROM player")
    suspend fun deletePlayers()

    @Query("DELETE FROM score")
    suspend fun deleteScores()
}