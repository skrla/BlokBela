package skrla.bela.blokbela.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import skrla.bela.blokbela.data.GameRepository
import skrla.bela.blokbela.data.model.Round
import skrla.bela.blokbela.data.model.Score
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(private val repository: GameRepository) : ViewModel() {

    var round: LiveData<Round> = repository.getCurrentRound()
    val score = repository.getScore().asLiveData()
    val scoreRound = mutableListOf<Score>()
    private val gamePoints = 162
    private val possibleCall = listOf(
        20,
        40,
        50,
        60,
        70,
        80,
        90,
        100,
        110,
        120,
        140,
        150,
        160,
        170,
        180,
        190,
        200,
        210,
        220,
        240,
        250,
        270,
        280,
        290,
        300,
        310,
        320,
        340,
        350,
        370,
        390,
        400
    )
    private var callerMinPoints = 81
    private var us = 0
    private var them = 0
    private var callUs = 0
    private var callThem = 0
    private val _roundScoreUs = MutableLiveData(0)
    val roundScoreUs: LiveData<Int>
        get() = _roundScoreUs
    private val _roundScoreThey = MutableLiveData(0)
    val roundScoreThey: LiveData<Int>
        get() = _roundScoreThey

    fun addRound(round: Round) {
        viewModelScope.launch {
            repository.insertRound(round)
        }
    }

    fun insertScore(caller: Boolean, belaUs: Boolean, belaThem: Boolean) {
        if (belaUs) {
            callUs += 20
        }
        if (belaThem) {
            callThem += 20
        }
        if(callUs > 0) {
            us += callUs
            callerMinPoints += callUs/2
        }
        if(callThem > 0) {
            them += callThem
            callerMinPoints += callThem/2
        }
        if (caller && us < callerMinPoints) {
            us = 0
            them = 162 + callUs + callThem
        }
        if (!caller && them < callerMinPoints) {
            us = 162 + callUs + callThem
            them = 0
        }
        _roundScoreUs.value = _roundScoreUs.value?.plus(us)
        _roundScoreThey.value = _roundScoreThey.value?.plus(them)
        callerMinPoints = 81
        val score = Score(0, us, them, 0, round.value!!.roundId)
        scoreRound.add(score)
        viewModelScope.launch {
            repository.insertScore(score)
        }
        checkScore()
    }

    fun validateInputs(pointUs: Int, pointsThem: Int, callUs: Int, callThem: Int): String {
        if (pointUs !in 0..gamePoints) {
            return "Bodovi tima MI nisu ispravni"
        }
        if (pointsThem !in 0..gamePoints) {
            return "Bodovi tima VI nisu ispravni"
        }
        if (pointUs + pointsThem != 162) {
            return "Nisu isravno unešeni bodovi"
        }
        if (callUs !in possibleCall && callUs != 0) {
            return "Zvanje tima MI nije ispravno"
        }
        if (callThem !in possibleCall && callThem != 0) {
            return "Zvanje tima VI nije ispravno"
        }
        if (callUs in possibleCall && callThem != 0) {
            return "Ne mogu oba tima imati zvanje"
        }
        if (callThem in possibleCall && callUs != 0) {
            return "Ne mogu oba tima imati zvanje"
        }
        us = pointUs
        them = pointsThem
        this.callUs = callUs
        this.callThem = callThem
        return ""
    }

    fun roundPoints(allRoundScore: List<Score>) {
        var us = 0
        var they = 0
        allRoundScore.forEach {
            if (round.value!!.roundId == it.currentRound) {
                us += it.team1
                they += it.team2
                scoreRound.add(it)
            }
        }
        _roundScoreUs.value = us
        _roundScoreThey.value = they
    }

    private fun checkScore() {
        if (roundScoreUs.value!! > 1001 || roundScoreThey.value!! > 1001) {
            val r = if (roundScoreUs.value!! > roundScoreThey.value!!) {
                Round(round.value!!.roundId + 1, round.value!!.winTeam1+1, round.value!!.winTeam2)
            } else {
                Round(round.value!!.roundId + 1, round.value!!.winTeam1, round.value!!.winTeam2+1)
            }
            _roundScoreUs.value = 0
            _roundScoreThey.value = 0
            scoreRound.clear()
            addRound(r)
        }
    }

    fun deleteData(){
        viewModelScope.launch {
            repository.let {
                it.deletePlayers()
                it.deleteTeams()
                it.deleteScores()
                it.deleteRounds()
            }
        }
        scoreRound.clear()
    }
}