package ma.skrla.blokbela.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val dealer: Boolean = false,
    val teamId: Int?
    )
