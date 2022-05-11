package skrla.bela.blokbela.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "round")
data class Round(
    @PrimaryKey(autoGenerate = true)
    val roundId: Int = 0,
    @NonNull @ColumnInfo(name = "player1")
    val player1: Int,
    @NonNull @ColumnInfo(name = "player2")
    val player2: Int,
    @ColumnInfo(name = "player3")
    val player3: Int,
)