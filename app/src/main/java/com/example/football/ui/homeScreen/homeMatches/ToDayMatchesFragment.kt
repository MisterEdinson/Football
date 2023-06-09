package com.example.football.ui.homeScreen.homeMatches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.football.R
import com.example.football.ui.homeScreen.HomeViewModel
import com.example.football.ui.homeScreen.SwipeNavigationMatches
import com.example.football.ui.homeScreen.adapters.MatchesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_to_day_matches.*

@AndroidEntryPoint
class ToDayMatchesFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var matchAdapter: MatchesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_to_day_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
//      SWIPE
        rvMatchDay.setOnTouchListener(object : SwipeNavigationMatches(view.context) {

            override fun onSwipeRight() {

                findNavController().navigate(R.id.action_toDayMatchesFragment_to_immediateMatchesFragment)
                Toast.makeText(
                    context,
                    "Матчи на ближайшие дни",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
//      END SWIPE
        viewModel.matchLiveData.observe(viewLifecycleOwner) {
            it.let {
                matchAdapter?.differ?.submitList(it)
            }
        }
    }

    private fun initAdapter() {
        matchAdapter = MatchesAdapter()
        rvMatchDay.apply {
            adapter = matchAdapter
        }
    }
}