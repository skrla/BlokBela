package ma.skrla.blokbela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ma.skrla.blokbela.graphs.MainNavigationGraph
import ma.skrla.blokbela.ui.theme.BlokBelaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlokBelaTheme {
                MainNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
