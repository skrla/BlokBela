package skrla.bela.blokbela.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scoreTwoPlayers")
data class ScoreTwoPlayers(
    @PrimaryKey(autoGenerate = true)
    val scoreId: Int = 0,
    @NonNull
    @ColumnInfo(name = "pointsPlayer1")
    val pointsPlayer1: Int,
    @NonNull
    @ColumnInfo(name = "pointsPlayer2")
    val pointsPlayer2: Int,
    @NonNull
    @ColumnInfo(name = "roundId")
    val currentRound: Int
)