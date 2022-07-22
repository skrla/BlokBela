package skrla.bela.blokbela.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.relations.TeamWithPlayer
import skrla.bela.blokbela.databinding.DealersBinding

class DealersTwoTeamsAdapter : ListAdapter<TeamWithPlayer, DealersTwoTeamsAdapter.DealersViewHolder>(DiffCallback) {

    private var onItemClickListener: ((TeamWithPlayer) -> Unit)? = null

    companion object DiffCallback : DiffUtil.ItemCallback<TeamWithPlayer>() {
        override fun areItemsTheSame(oldItem: TeamWithPlayer, newItem: TeamWithPlayer): Boolean {
            return oldItem.team == newItem.team
        }

        override fun areContentsTheSame(oldItem: TeamWithPlayer, newItem: TeamWithPlayer): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    inner class DealersViewHolder(private var binding: DealersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamWithPlayer: TeamWithPlayer) {
            binding.let {
                it.teamNameTxt.text = teamWithPlayer.team.name
                it.player1TeamTxt.text = teamWithPlayer.player.first().name
                it.player2TeamTxt.text = teamWithPlayer.player.last().name
                it.checkBoxPlayer1.isChecked = teamWithPlayer.player.first().dealer
                it.checkBoxPlayer2.isChecked = teamWithPlayer.player.last().dealer
                it.executePendingBindings()
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(teamWithPlayer)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealersViewHolder {
        return DealersViewHolder(
            DealersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: DealersViewHolder, position: Int) {
        val dealerId = getItem(position)
        holder.bind(dealerId)
    }

    fun moveItem(from: Int, to: Int) {

        val list = currentList.toMutableList()
        val fromLocation = list[from]
        list.removeAt(from)
        if (to < from) {
            list.add(to + 1 , fromLocation)
        } else {
            list.add(to - 1, fromLocation)
        }
        submitList(list)

    }
}