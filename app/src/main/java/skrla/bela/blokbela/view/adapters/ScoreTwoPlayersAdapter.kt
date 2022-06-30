package skrla.bela.blokbela.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.Score
import skrla.bela.blokbela.databinding.ScoreItemBinding

class ScoreTwoPlayersAdapter : ListAdapter<Score, ScoreTwoPlayersAdapter.ScoreViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Score>() {
        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem.scoreId == newItem.scoreId
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    class ScoreViewHolder(private var binding: ScoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(score: Score) {
            binding.score = score
            binding.order.text = score.scoreId.toString() + "."
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(
            ScoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val roundId = getItem(position)
        holder.bind(roundId)
    }
}