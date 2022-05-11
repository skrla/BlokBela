package skrla.bela.blokbela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import skrla.bela.blokbela.databinding.FragmentGameBinding
import skrla.bela.blokbela.viewmodel.ScoreViewModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val scoreViewModel: ScoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val trumpThey = binding.switchTrumpThey
        val trumpUs = binding.switchTrumpUs
        val belaUs = binding.switchBelaUs
        val belaThey = binding.switchBelaThey
        trumpThey.setOnClickListener {
            if (trumpThey.isPressed && trumpUs.isChecked) {
                trumpUs.isChecked = false
            } else if (trumpThey.isPressed && !(trumpThey.isChecked)) {
                trumpUs.isChecked = true
            }
        }
        trumpUs.setOnClickListener {
            if (trumpUs.isPressed && trumpThey.isChecked) {
                trumpThey.isChecked = false
            } else if (trumpUs.isPressed && !(trumpUs.isChecked)) {
                trumpThey.isChecked = true
            }
        }

        belaThey.setOnClickListener {
            if (belaThey.isPressed && belaUs.isChecked) {
                belaUs.isChecked = false
            }
        }
        belaUs.setOnClickListener {
            if (belaUs.isPressed && belaThey.isChecked) {
                belaThey.isChecked = false
            }
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}