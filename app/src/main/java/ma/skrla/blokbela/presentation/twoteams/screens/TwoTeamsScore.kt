package ma.skrla.blokbela.presentation.twoteams.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ma.skrla.blokbela.domain.Score
import ma.skrla.blokbela.presentation.twoteams.viewModel.TwoTeamsViewModel

@Composable
fun TwoTeamsScore(
    navController: NavController,
    modifier: Modifier,
    viewModel: TwoTeamsViewModel = hiltViewModel()
) {
    val score by viewModel.score.collectAsStateWithLifecycle()
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(
            contentPadding = PaddingValues(10.dp)
        ) {
            val scoreCount = score.size / 2
            items(scoreCount) {
                ScoreRow(rowIndex = it, score)
            }
        }
    }

}

@Composable
fun ScoreRow(
    rowIndex: Int,
    scoreList: List<Score>
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.weight(1f)) {
            Text(text = "text1")
        }
        Column(Modifier.weight(1f)) {
            Text(text = "text2")
        }
    }
}