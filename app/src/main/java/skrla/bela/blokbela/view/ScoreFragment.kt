package skrla.bela.blokbela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import skrla.bela.blokbela.databinding.FragmentScoreBinding
import skrla.bela.blokbela.view.adapters.ScoreTwoPlayersAdapter
import skrla.bela.blokbela.viewmodel.ScoreViewModel

class ScoreFragment : Fragment() {
    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        val adapter = ScoreTwoPlayersAdapter()
        binding.let {
            it.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerView.adapter = adapter
        }

        scoreViewModel.score.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}