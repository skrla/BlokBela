package skrla.bela.blokbela.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "round")
data class Round(
    @PrimaryKey(autoGenerate = true)
    val roundId: Int = 0,
    val winTeam1: Int = 0,
    val winTeam2: Int = 0,
    val winPlayer3: Int = 0
)