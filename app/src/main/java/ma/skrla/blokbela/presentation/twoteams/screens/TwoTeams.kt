package ma.skrla.blokbela.presentation.twoteams.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ma.skrla.blokbela.graphs.TwoTeamsNavigationGraph
import ma.skrla.blokbela.presentation.twoteams.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTeams(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {TwoTeamsBottomBar(navController)}
    ) {
        TwoTeamsNavigationGraph(navController = navController, Modifier.padding(it))
    }
}

@Composable
fun TwoTeamsBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Dealar,
        BottomBarScreen.Game,
        BottomBarScreen.Score
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    NavigationBar {
        screens.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {Text(text = screen.title)},
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation Item"
            )
               },
        selected = currentDestination?.hierarchy?.any {
                                                      it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
        )
}
