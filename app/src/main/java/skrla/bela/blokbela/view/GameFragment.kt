package skrla.bela.blokbela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
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
        scoreViewModel.round.observe(viewLifecycleOwner) {
            if (it == null) {
                val r = Round(1)
                scoreViewModel.addRound(r)
            }
        }
        val trumpThey = binding.switchTrumpThey
        val trumpUs = binding.switchTrumpUs
        val belaUs = binding.switchBelaUs
        val belaThey = binding.switchBelaThey
        val btn = binding.btnAdd
        val pointUs = binding.editTextPointUs
        val pointThem = binding.editTextPointThey

        pointUs.doAfterTextChanged { it ->
            if(!it.toString().equals("") && pointUs.hasFocus()) {
                pointThem.setText((162 - it.toString().toInt()).toString())
            }
            if(it.toString().equals("") && pointUs.hasFocus()) {
                pointThem.setText("162")
            }
        }

        pointThem.doAfterTextChanged { it ->
            if(!it.toString().equals("") && pointThem.hasFocus()) {
                pointUs.setText((162 - it.toString().toInt()).toString())
            }
            if(it.toString().equals("") && pointThem.hasFocus()) {
                pointUs.setText("162")
            }
        }

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
        btn.setOnClickListener {
            validate(pointUs, pointThem, trumpUs, belaUs, belaThey)
            setValues(pointUs, pointThem, belaUs, belaThey)
        }

        return binding.root
    }


    private fun validate(
        pointUs: TextInputEditText,
        pointThem: TextInputEditText,
        trumpUs: SwitchMaterial,
        belaUs: SwitchMaterial,
        belaThey: SwitchMaterial
    ) {
        val numPointUs = if (pointUs.text.toString() != "") pointUs.text.toString() else "0"
        val numPointThem = if(pointThem.text.toString() != "") pointThem.text.toString() else "0"
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
        if(!mistake.equals("")) {
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