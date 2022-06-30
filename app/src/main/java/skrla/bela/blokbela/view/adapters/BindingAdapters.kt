package skrla.bela.blokbela.view.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.Score

@BindingAdapter("scoreTwoTeams")
fun bindScoreTwoTeams(recyclerView: RecyclerView, data: List<Score>) {
    val adapter = recyclerView.adapter as ScoreTwoPlayersAdapter
    adapter.submitList(data)
}

@BindingAdapter("scoreTeam1")
fun TextView.scoreNumberTeam1(score: Score) {
    text = score.team1.toString()
}

@BindingAdapter("scoreTeam2")
fun TextView.scoreNumberTeam2(score: Score) {
    text = score.team2.toString()
}