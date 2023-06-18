package ma.skrla.blokbela.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ma.skrla.blokbela.presentation.twoteams.screens.TwoTeamsDealers
import ma.skrla.blokbela.presentation.twoteams.screens.TwoTeamsGame
import ma.skrla.blokbela.presentation.twoteams.screens.TwoTeamsScore

@Composable
fun TwoTeamsNavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        route = "two_teams",
        startDestination = "two_teams_game"
    ) {
        composable("two_teams_game") {
            TwoTeamsGame(navController = navController, modifier)
        }
        composable("two_teams_score") {
            TwoTeamsScore(navController = navController, modifier)
        }
        composable("two_teams_dealer") {
            TwoTeamsDealers(navController = navController, modifier)
        }
    }
}