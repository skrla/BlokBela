package skrla.bela.blokbela.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import skrla.bela.blokbela.data.GameRepository
import skrla.bela.blokbela.data.model.Round
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {

    var round = 1
    val score = repository.getScore().asLiveData()
    private val gamePoints = 162
    private val possibleCall = listOf(20, 40, 50, 60, 70, 80, 90, 100, 110, 120, 140, 150, 160, 170, 180, 190, 200, 210, 220, 240, 250, 270, 280, 290, 300, 310, 320, 340, 350, 370, 390, 400)
    private var callerMinPoints = 81

    fun insertRound(round: Round) = viewModelScope.launch {
        repository.insertRound(round)
    }
}