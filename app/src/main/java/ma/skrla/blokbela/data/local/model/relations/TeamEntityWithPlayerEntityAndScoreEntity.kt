package ma.skrla.blokbela.data.local.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity
import ma.skrla.blokbela.data.local.model.TeamEntity

data class TeamEntityWithPlayerEntityAndScoreEntity(
    @Embedded val team: TeamEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val players: List<PlayerEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val score: List<ScoreEntity> = listOf()
)
