package skrla.bela.blokbela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import skrla.bela.blokbela.data.model.Round
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
        binding.let {
            it.lifecycleOwner = this
            it.scoreViewModel = scoreViewModel
        }
        scoreViewModel.let { itModel ->
            itModel.round.observe(viewLifecycleOwner) {
                if (it == null) {
                    val r = Round(1)
                    scoreViewModel.addRound(r)
                }
            }
            itModel.score.observe(viewLifecycleOwner) {
                if (it != null) {
                    scoreViewModel.roundPoints(it)
                    scoreViewModel.score.removeObservers(viewLifecycleOwner)
                }
            }
        }



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val pointUs = binding.editTextPointUs
        val pointThem = binding.editTextPointThey
        val trumpUs = binding.switchTrumpUs
        val trumpThey = binding.switchTrumpThey
        val belaUs = binding.switchBelaUs
        val belaThey = binding.switchBelaThey
        val btn = binding.btnAdd


        pointUs.doAfterTextChanged {
            autocomplete(pointUs, pointThem)
        }

        pointThem.doAfterTextChanged {
            autocomplete(pointThem, pointUs)
        }

        trumpUs.setOnCheckedChangeListener { _, isChecked ->
            trumpThey.isChecked = !isChecked
        }

        trumpThey.setOnCheckedChangeListener { _, isChecked ->
            trumpUs.isChecked = !isChecked
        }

        belaUs.setOnClickListener {
            bela(belaUs, belaThey)
        }

        belaThey.setOnClickListener {
            bela(belaThey, belaUs)
        }

        btn.setOnClickListener {
            validate(pointUs, pointThem, trumpUs, belaUs, belaThey)
            setValues(pointUs, pointThem, belaUs, belaThey)
        }
    }

    private fun bela(bela1: SwitchMaterial, bela2: SwitchMaterial) {
        if (bela1.isPressed && bela2.isChecked) {
            bela2.isChecked = false
        }
    }

    private fun autocomplete(
        text1: TextInputEditText,
        text2: TextInputEditText,
    ) {
        if (text1.hasFocus()) {
            if (text1.text.toString() == "") {
                text2.setText("162")
            } else {
                text2.setText((162 - text1.text.toString().toInt()).toString())
            }
        }
    }

    private fun validate(
        pointUs: TextInputEditText,
        pointThem: TextInputEditText,
        trumpUs: SwitchMaterial,
        belaUs: SwitchMaterial,
        belaThey: SwitchMaterial
    ) {
        val numPointUs = if (pointUs.text.toString() != "") pointUs.text.toString() else "0"
        val numPointThem = if (pointThem.text.toString() != "") pointThem.text.toString() else "0"
        val callUs = if (binding.editTextCallUs.text.toString() != ""
        ) binding.editTextCallUs.text.toString() else "0"
        val callThem = if (binding.editTextCallThey.text.toString() != ""
        ) binding.editTextCallThey.text.toString() else "0"
        val mistake = scoreViewModel.validateInputs(
            numPointUs.toInt(),
            numPointThem.toInt(),
            callUs.toInt(),
            callThem.toInt()
        )
        if (mistake != "") {
            Toast.makeText(context, mistake, Toast.LENGTH_LONG).show()
            return
        }
        scoreViewModel.insertScore(trumpUs.isChecked, belaUs.isChecked, belaThey.isChecked)
    }

    private fun setValues(
        pointUs: TextInputEditText,
        pointThem: TextInputEditText,
        belaUs: SwitchMaterial,
        belaThey: SwitchMaterial
    ) {
        pointUs.setText("0")
        pointThem.setText("0")
        binding.editTextCallUs.setText("0")
        binding.editTextCallThey.setText("0")
        belaUs.isChecked = false
        belaThey.isChecked = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}