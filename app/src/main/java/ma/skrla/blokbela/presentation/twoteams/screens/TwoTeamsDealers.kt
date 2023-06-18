package ma.skrla.blokbela.presentation.twoteams.screens

import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ma.skrla.blokbela.domain.Player
import ma.skrla.blokbela.domain.Team
import ma.skrla.blokbela.presentation.twoteams.viewModel.TwoTeamsViewModel

@Composable
fun TwoTeamsDealers(
    navController: NavController,
    modifier: Modifier,
    viewModel: TwoTeamsViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        val twoTeams by viewModel.twoTeamsData.collectAsState()
        if (twoTeams.isNotEmpty()) {
            Column(modifier = modifier) {
                TeamPlayerList(team = twoTeams[0], modifier = Modifier)
                TeamPlayerList(team = twoTeams[1], modifier = Modifier)
            }

        }
    }
}

@Composable
fun TeamPlayerList(
    team: Team,
    modifier: Modifier
) {
    PlayerRow(team.players[0], modifier)
    PlayerRow(team.players[1], modifier)
}

@Composable
fun PlayerRow(
    player: Player,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(20.dp)
        ) {
            Text(text = player.name)
        }
        Column(
            Modifier
                .weight(1f)
                .padding(20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Checkbox(checked = player.dealer, onCheckedChange = {})
        }

    }

}