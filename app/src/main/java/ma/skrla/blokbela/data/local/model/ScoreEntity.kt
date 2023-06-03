package ma.skrla.blokbela.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val round: Int,
    val points: Int,
    val playerId: Int?,
    val teamId: Int?
)
