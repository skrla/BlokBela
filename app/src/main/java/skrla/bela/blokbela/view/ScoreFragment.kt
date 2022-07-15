package skrla.bela.blokbela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import skrla.bela.blokbela.databinding.FragmentScoreBinding
import skrla.bela.blokbela.view.adapters.ScoreTwoTeamsAdapter
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
        binding.let {
            it.lifecycleOwner = this
            it.scoreViewModel = scoreViewModel
            it.recyclerView.adapter = ScoreTwoTeamsAdapter()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}