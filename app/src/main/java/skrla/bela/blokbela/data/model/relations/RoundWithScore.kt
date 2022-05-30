package skrla.bela.blokbela.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score

data class RoundWithScore(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "roundId",
        entityColumn = "roundId"
    )
    val score: List<Score>
)