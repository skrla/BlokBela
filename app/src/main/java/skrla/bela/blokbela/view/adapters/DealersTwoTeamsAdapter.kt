package skrla.bela.blokbela.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.databinding.DealersBinding

class DealersTwoTeamsAdapter  : ListAdapter<Player, DealersTwoTeamsAdapter.DealersViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.playerId == newItem.playerId
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    class DealersViewHolder(private var binding: DealersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.let {
                it.player = player
//                it.playerNameTxt.text = "Igrac"
//                it.teamNameTxt.text = "Mi"
                it.executePendingBindings()
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
}