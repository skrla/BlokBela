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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ma.skrla.blokbela.domain.Team
import ma.skrla.blokbela.presentation.twoteams.viewModel.TwoTeamsViewModel

@Composable
fun TwoTeamsGame(
    navController: NavController,
    modifier: Modifier,
    viewModel: TwoTeamsViewModel = hiltViewModel()
) {
    val twoTeamsData by viewModel.twoTeamsData.collectAsStateWithLifecycle()
    if (twoTeamsData.isNullOrEmpty()) {
        viewModel.addTeams()
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        if (twoTeamsData.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                GameData(teams = twoTeamsData, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun GameData(
    teams: List<Team>,
    viewModel: TwoTeamsViewModel
) {
    val we = teams[0]
    val you = teams[1]

    var weTrump by remember {
        mutableStateOf(teams[0].trump)
    }
    var youTrump by remember {
        mutableStateOf(teams[1].trump)
    }
    var weBela by remember {
        mutableStateOf(teams[0].bela)
    }
    var youBela by remember {
        mutableStateOf(teams[1].bela)
    }
    var weCall by remember {
        mutableStateOf(teams[0].call)
    }
    var youCall by remember {
        mutableStateOf(teams[1].call)
    }
    var weScore by remember {
        mutableStateOf(0)
    }
    var youScore by remember {
        mutableStateOf(0)
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        TeamName(teams = teams, modifier = Modifier.weight(1f))
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        TrumpCaller(
            we = weTrump,
            you = youTrump,
            modifier = Modifier.weight(1f),
            onTrumpChange = {
                weTrump = !weTrump
                we.trump = weTrump
                youTrump = !youTrump
                you.trump = youTrump
            }
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        BelaCaller(
            we = weBela,
            you = youBela,
            modifier = Modifier.weight(1f),
            onBelaChange = { weBelaS, youBelaS ->
                weBela = weBelaS
                we.bela = weBela
                youBela = youBelaS
                you.bela = youBela
            }
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        CallInput(
            we = weCall,
            you = youCall,
            modifier = Modifier.weight(1f),
            onCallChange = { weCallI, youCallI ->
                weCall = weCallI
                we.call = weCall
                youCall = youCallI
                you.call = youCall
            }
        )
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        ScoreInput(
            we = weScore,
            you = youScore,
            modifier = Modifier.weight(1f),
            onScoreChange = { weScoreT, youScoreT ->
                weScore = weScoreT
                youScore = youScoreT
            }
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Button(onClick = {
        viewModel.insertScore(we, you, weScore, youScore)
        weScore = 0
        youScore = 0
        youTrump = !youTrump
        youBela = false
        youCall = 0
        weTrump = !weTrump
        weBela = false
        weCall = 0
    }) {
        Text(text = "Spremi")
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
                if(value.isEmpty()) {
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
                    onScoreChange(weScore,  162 - weScore)
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
                    onScoreChange(162-youScore, youScore)
                }
                if(value.isEmpty()){
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

