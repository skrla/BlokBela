package ma.skrla.blokbela.domain

data class Player(
    val id: Int,
    var name: String,
    var dealer: Boolean = false,
    var score: List<Score>?
)
