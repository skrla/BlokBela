package ma.skrla.blokbela.presentation.twoteams

import ma.skrla.blokbela.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Dealar : BottomBarScreen(
        route = "two_teams_dealer",
        title = "Dealer",
        icon = R.drawable.ic_delar
    )

    object Game : BottomBarScreen(
        route = "two_teams_game",
        title = "Game",
        icon = R.drawable.baseline_game
    )

    object Score : BottomBarScreen(
        route = "two_teams_score",
        title = "Score",
        icon = R.drawable.baseline_score
    )
}
