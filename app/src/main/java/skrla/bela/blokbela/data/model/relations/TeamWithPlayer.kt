package skrla.bela.blokbela.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.data.model.Team

data class TeamWithPlayer(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "teamId"
    )
    val player: List<Player>
)