package skrla.bela.blokbela.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.databinding.DealersBinding

class DealersTwoTeamsAdapter  : ListAdapter<Player, DealersTwoTeamsAdapter.DealersViewHolder>(DiffCallback) {

    private var onItemClickListener: ((Player) -> Unit)? = null
    private val differ = AsyncListDiffer(this, DiffCallback)

    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.playerId == newItem.playerId
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    inner class DealersViewHolder(private var binding: DealersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.let {
                it.player = player
                it.executePendingBindings()
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(player)
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

    fun setOnItemClickListener(listener: (Player) -> Unit) {
        onItemClickListener = listener
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