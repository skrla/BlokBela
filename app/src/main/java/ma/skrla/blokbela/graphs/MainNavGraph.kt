package ma.skrla.blokbela.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ma.skrla.blokbela.presentation.MainScreen
import ma.skrla.blokbela.presentation.twoteams.screens.TwoTeams

@Composable
fun MainNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = "main_graph",
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        composable("two_teams") {
            TwoTeams()
        }
    }
}