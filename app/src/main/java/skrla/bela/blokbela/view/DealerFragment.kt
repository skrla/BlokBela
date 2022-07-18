package skrla.bela.blokbela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.databinding.FragmentDealerBinding
import skrla.bela.blokbela.view.adapters.DealersTwoTeamsAdapter
import skrla.bela.blokbela.viewmodel.ScoreViewModel


class DealerFragment : Fragment() {

    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private var _binding: FragmentDealerBinding? = null
    private val binding get() = _binding!!
    private val itemTouchHelper by lazy {
        val simpleItemTouchHelper = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter = recyclerView.adapter as DealersTwoTeamsAdapter

                val from = viewHolder.adapterPosition

                val to = target.adapterPosition

                adapter.moveItem(from, to)
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)

                viewHolder.itemView.alpha = 1.0f
            }
        }
        ItemTouchHelper(simpleItemTouchHelper)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDealerBinding.inflate(inflater, container, false)

        binding.let {
            it.lifecycleOwner = this
            it.scoreViewModel = scoreViewModel
            it.twoTeamsDealrsRec.adapter = DealersTwoTeamsAdapter()
        }
        itemTouchHelper.attachToRecyclerView(binding.twoTeamsDealrsRec)

        return binding.root
    }


}