package ma.skrla.blokbela.presentation.twoteams.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ma.skrla.blokbela.domain.Score
import ma.skrla.blokbela.domain.Team
import ma.skrla.blokbela.presentation.twoteams.viewModel.TwoTeamsViewModel

@Composable
fun TwoTeamsGame(
    navController: NavController,
    modifier: Modifier,
    viewModel: TwoTeamsViewModel = hiltViewModel()
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            GameData(viewModel = viewModel)

        }
    }
}

@Composable
fun GameData(
    viewModel: TwoTeamsViewModel
) {
    val twoTeamsData = viewModel.teams.collectAsStateWithLifecycle()
    if (twoTeamsData.value.isNullOrEmpty()) {
        viewModel.addTeams()
    }
    if (!twoTeamsData.value.isNullOrEmpty()) {
        val (we, setWe) = remember {
            mutableStateOf(twoTeamsData.value[0])
        }
        val (you, setYou) = remember {
            mutableStateOf(twoTeamsData.value[1])

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            TeamName(teams = twoTeamsData.value, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            TrumpCaller(
                we = we.trump,
                you = you.trump,
                modifier = Modifier.weight(1f),
                onTrumpChange = {
                    val newWe = we.copy()
                    val newYou = you.copy()
                    newWe.trump = !newWe.trump
                    setWe(newWe)
                    newYou.trump = !newYou.trump
                    setYou(newYou)
                }
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            BelaCaller(
                we = we.bela,
                you = you.bela,
                modifier = Modifier.weight(1f),
                onBelaChange = { weBelaS, youBelaS ->
                    val newWe = we.copy()
                    val newYou = you.copy()
                    newWe.bela = weBelaS
                    setWe(newWe)
                    newYou.bela = youBelaS
                    setYou(newYou)
                }
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            CallInput(
                we = we.call,
                you = you.call,
                modifier = Modifier.weight(1f),
                onCallChange = { weCallI, youCallI ->
                    val newWe = we.copy()
                    val newYou = you.copy()
                    newWe.call = weCallI
                    setWe(newWe)
                    newYou.call = youCallI
                    setYou(newYou)
                }
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            ScoreInput(
                we = we.score.last().points,
                you = you.score.last().points,
                modifier = Modifier.weight(1f),
                onScoreChange = { weScoreT, youScoreT ->
                    val newWe = we.copy()
                    val newYou = you.copy()
                    newWe.score.last().points = weScoreT
                    setWe(newWe)
                    you.score.last().points = youScoreT
                    setYou(newYou)
                }
            )
        }


        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
//            viewModel.updateScore(we.score.last(), you.score.last())

        }) {
            Text(text = "Spremi")
        }
    }

}


@Composable
fun TeamName(
    teams: List<Team>,
    modifier: Modifier
) {
    teams.forEach { team ->
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = team.name)
        }
    }
}

@Composable
fun TrumpCaller(
    we: Boolean,
    you: Boolean,
    modifier: Modifier,
    onTrumpChange: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Adut")
        Switch(checked = we, onCheckedChange = {
            onTrumpChange()
        })

    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Adut")
        Switch(checked = you, onCheckedChange = {
            onTrumpChange()
        })

    }
}

@Composable
fun BelaCaller(
    we: Boolean,
    you: Boolean,
    modifier: Modifier,
    onBelaChange: (Boolean, Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Zvanje")
        Switch(checked = we, onCheckedChange = {
            onBelaChange(!we, false)
        })
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Zvanje")
        Switch(checked = you, onCheckedChange = {
            onBelaChange(false, !you)
        })
    }
}

@Composable
fun CallInput(
    we: Int,
    you: Int,
    modifier: Modifier,
    onCallChange: (Int, Int) -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = we.toString(),
            onValueChange = { value ->
                if (value.isNotEmpty()) {
                    onCallChange(value.filter { it.isDigit() }.toInt(), 0)
                }
                if (value.isEmpty()) {
                    onCallChange(0, 0)
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )

    }
    Box(modifier = modifier) {
        BasicTextField(
            value = you.toString(),
            onValueChange = { value ->
                if (value.isNotEmpty()) {
                    onCallChange(0, value.filter { it.isDigit() }.toInt())
                }
                if (value.isEmpty()) {
                    onCallChange(0, 0)
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )

    }
}

@Composable
fun ScoreInput(
    we: Int,
    you: Int,
    modifier: Modifier,
    onScoreChange: (Int, Int) -> Unit
) {

    Box(modifier = modifier) {
        BasicTextField(
            value = we.toString(),
            onValueChange = { value ->
                if (value.isNotEmpty()) {
                    var weScore = value.filter { it.isDigit() }.toInt()
                    if (weScore > 162) {
                        weScore = 162
                    }
                    onScoreChange(weScore, 162 - weScore)
                }
                if (value.isEmpty()) {
                    onScoreChange(0, 162)
                }


            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )

    }
    Box(modifier = modifier) {
        BasicTextField(
            value = you.toString(),
            onValueChange = { value ->
                if (value.isNotEmpty()) {
                    var youScore = value.filter { it.isDigit() }.toInt()
                    if (youScore > 162) {
                        youScore = 162
                    }
                    onScoreChange(162 - youScore, youScore)
                }
                if (value.isEmpty()) {
                    onScoreChange(162, 0)
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )

    }
}

