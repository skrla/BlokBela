package ma.skrla.blokbela.domain

data class Team(
    val id: Int,
    val name: String,
    val players: List<Player>,
    var score: List<Score>,
    var call: Int = 0,
    var trump: Boolean,
    var bela: Boolean = false
)
