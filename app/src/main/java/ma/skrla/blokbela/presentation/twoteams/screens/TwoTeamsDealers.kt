package ma.skrla.blokbela.presentation.twoteams.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ma.skrla.blokbela.domain.Player
import ma.skrla.blokbela.domain.Team
import ma.skrla.blokbela.presentation.twoteams.viewModel.TwoTeamsViewModel
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import kotlin.math.roundToInt

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TwoTeamsDealers(
    navController: NavController,
    modifier: Modifier,
    viewModel: TwoTeamsViewModel = hiltViewModel()
) {
    val playersData by viewModel.playersData.collectAsStateWithLifecycle()
    val players = remember {
        mutableStateOf(playersData)
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {

        if (playersData.isNotEmpty()) {
            val state = rememberReorderableLazyListState(onMove = { from, to ->
                players.value = players.value.toMutableList().apply {
                    add(to.index, removeAt(from.index))
                }
            })
            LazyColumn(
                state = state.listState,
                modifier = Modifier
                    .reorderable(state)
                    .detectReorderAfterLongPress(state)
            ) {
                items(players.value.size, {it}) {item ->
                    ReorderableItem(reorderableState = state, key = item) {isDragging ->
                        val evevation = animateDpAsState(if(isDragging) 16.dp else 0.dp)
                        PlayerRow(player = players.value[item], modifier = Modifier)
                    }
                }
            }

        } else {
            Text(text = "player data empty")
        }
    }
}

@Composable
fun DraggableRow(
    item: Player
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
    ) {
        Text(text = item.name)
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
        modifier = modifier
            .fillMaxWidth()
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