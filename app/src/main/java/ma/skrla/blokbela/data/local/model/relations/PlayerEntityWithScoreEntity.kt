package ma.skrla.blokbela.data.local.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity

data class PlayerEntityWithScoreEntity(
    @Embedded val player: PlayerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "playerId"
    )
    val score: List<ScoreEntity>
)
