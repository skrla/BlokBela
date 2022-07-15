package skrla.bela.blokbela.data


import androidx.lifecycle.LiveData
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.data.model.Team

interface GameRepository {

    fun getScore(): Flow<List<Score>>

    fun getCurrentRound(): LiveData<Round>

    fun getPlayers(): Flow<List<Player>>

    suspend fun insertScore(score: Score)

    suspend fun insertRound(round: Round)

    suspend fun insertPlayer(player: Player)

    suspend fun insertTeam(team: Team)

    suspend fun deletePlayers()

    suspend fun deleteTeams()

    suspend fun deleteScores()

    suspend fun deleteRounds()
}