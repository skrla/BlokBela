package skrla.bela.blokbela.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val scoreId: Int = 0,
    @NonNull
    @ColumnInfo(name = "team1")
    val team1: Int,
    @NonNull
    @ColumnInfo(name = "team2")
    val team2: Int,
    @ColumnInfo(name = "pointsPlayer3")
    val pointsPlayer3: Int,
    @NonNull
    @ColumnInfo(name = "roundId")
    val currentRound: Int
)