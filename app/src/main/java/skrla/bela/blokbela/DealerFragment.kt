package skrla.bela.blokbela

import android.os.Bundle
import skrla.bela.blokbela.DealerFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import skrla.bela.blokbela.R


class DealerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dealer, container, false)
    }

}