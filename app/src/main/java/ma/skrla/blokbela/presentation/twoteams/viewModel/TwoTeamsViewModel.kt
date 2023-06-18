package ma.skrla.blokbela.presentation.twoteams.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.TeamEntity
import ma.skrla.blokbela.data.mapper.toScore
import ma.skrla.blokbela.data.mapper.toScoreEntity
import ma.skrla.blokbela.data.mapper.toTeam
import ma.skrla.blokbela.data.mapper.toTeamEntity
import ma.skrla.blokbela.domain.Score
import ma.skrla.blokbela.domain.Team
import ma.skrla.blokbela.repository.BelaRepository
import javax.inject.Inject

@HiltViewModel
class TwoTeamsViewModel @Inject constructor(private val repository: BelaRepository) : ViewModel() {

    private val _twoTeamsData = MutableStateFlow<List<Team>>(
        emptyList()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val score = repository.getScore().mapLatest { scoreList ->
        scoreList.map { it.toScore() }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val twoTeamsData : StateFlow<List<Team>> = _twoTeamsData.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getTeamWithPlayerAndScore().collectLatest {it ->
                _twoTeamsData.value = it.map { it.toTeam(it.team.id == 1) }
                println("Flow is active")
            }
        }
    }

    fun addTeams() {
        for (i in 1..2) {
            val name = if (i % 2 == 1) "Mi" else "Vi"
            val trump = i % 2 == 1
            val newTeam = TeamEntity(id = i, name = name)
            viewModelScope.launch {
                repository.insertTeam(newTeam)
            }
        }
        addPlayers()
    }

    private fun addPlayers() {
        for (i in 0..3) {
            val name = "Igrac${i + 1}"
            val targetTeam = if (i % 2 == 0) 1 else 2
            val dealer = i == 0
            val player = PlayerEntity(id = i + 1, name = name, teamId = targetTeam, dealer = dealer)
            viewModelScope.launch {
                repository.insertPlayer(player)
            }
        }
    }

    fun insertScore(we: Team, you: Team, weScore: Int, youScore: Int) {
        var sumWe = weScore + we.call + if (we.bela) 20 else 0
        var sumYou = youScore + you.call + if (you.bela) 20 else 0
        if (sumWe < sumYou && we.trump) {
           sumYou += sumWe
           sumWe = 0
        }
        if(sumYou < sumWe && you.trump) {
            sumWe += sumYou
            sumYou = 0
        }
        val scoreWe = Score(0, 1, sumWe).toScoreEntity(null, we.id)
        val scoreYou = Score(0, 1, sumYou).toScoreEntity(null, you.id)
        viewModelScope.launch {
            repository.insertScore(scoreWe)
            repository.insertScore(scoreYou)
        }
    }

    fun changeTrump() {
        twoTeamsData.value[0].trump = !twoTeamsData.value[0].trump
        twoTeamsData.value[1].trump = !twoTeamsData.value[1].trump
    }
}