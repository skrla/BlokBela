package skrla.bela.blokbela.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "round")
data class Round(
    @PrimaryKey(autoGenerate = true)
    val roundId: Int = 0
)