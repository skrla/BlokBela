package skrla.bela.blokbela.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey(autoGenerate = true)
    val teamId: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @NonNull
    @ColumnInfo(name = "roundId")
    val currentRound: Int
)
