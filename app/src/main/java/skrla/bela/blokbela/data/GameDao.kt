package skrla.bela.blokbela.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.data.model.Team

@Dao
interface GameDao {

    @Query("SELECT * FROM round order by roundId DESC limit 1")
    fun getCurrentRound(): LiveData<Round>

    @Query("SELECT * FROM score")
    fun getScore(): Flow<List<Score>>

    @Query("SELECT * FROM player")
    fun getPlayers(): Flow<List<Player>>

    @Insert
    suspend fun insertScore(score: Score)

    @Insert
    suspend fun insertPlayer(player: Player)

    @Insert
    suspend fun insertTeam(team: Team)

    @Insert
    suspend fun insertRound(round: Round)

    @Query("DELETE FROM player")
    suspend fun deletePlayers()

    @Query("DELETE FROM team")
    suspend fun deleteTeams()

    @Query("DELETE FROM score")
    suspend fun deleteScores()

    @Query("DELETE FROM round")
    suspend fun deleteRounds()
}