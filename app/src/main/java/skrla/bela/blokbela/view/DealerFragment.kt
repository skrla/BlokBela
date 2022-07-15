package skrla.bela.blokbela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import skrla.bela.blokbela.data.model.Player
import skrla.bela.blokbela.databinding.FragmentDealerBinding
import skrla.bela.blokbela.view.adapters.DealersTwoTeamsAdapter
import skrla.bela.blokbela.viewmodel.ScoreViewModel


class DealerFragment : Fragment() {

    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private var _binding: FragmentDealerBinding? = null
    private val binding get() = _binding!!

    lateinit var players: List<Player>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDealerBinding.inflate(inflater, container, false)
        scoreViewModel.addTeams()
        scoreViewModel.addPlayers(4)
        scoreViewModel.players.observe(viewLifecycleOwner) {
            if(it != null) {
                players = it
            }
        }
        binding.let {
            it.lifecycleOwner = this
            it.twoTeamsDealrsRec.adapter = DealersTwoTeamsAdapter()
            it.twoTeamsDealrsRec.hasFixedSize()
        }



        return binding.root
    }


}