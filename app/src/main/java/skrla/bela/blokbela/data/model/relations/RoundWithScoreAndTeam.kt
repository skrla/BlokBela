package skrla.bela.blokbela.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.data.model.Team

data class RoundWithScoreAndTeam(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "roundId",
        entityColumn = "roundId"
    )
    val score: List<Score>,
    @Relation(
        parentColumn = "roundId",
        entityColumn = "roundId"
    )
    val team: List<Team>
)