package skrla.bela.blokbela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import skrla.bela.blokbela.R
import skrla.bela.blokbela.databinding.FragmentStartingBinding
import skrla.bela.blokbela.viewmodel.ScoreViewModel

@AndroidEntryPoint
class StartingFragment : Fragment() {
    private var _binding: FragmentStartingBinding? = null
    private val binding get() = _binding!!


    private val scoreViewModel: ScoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartingBinding.inflate(inflater, container, false)
        val btnNewGame = binding.btnNewGame
        val btnOldGame = binding.btnOldGame
        btnNewGame.setOnClickListener {
            scoreViewModel.deleteData()
            findNavController().navigate(R.id.action_startingFragment_to_gameFragment)
        }
        btnOldGame.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_gameFragment)
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}