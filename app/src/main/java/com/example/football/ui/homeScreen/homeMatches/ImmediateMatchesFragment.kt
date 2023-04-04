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
import com.example.football.ui.homeScreen.adapters.Matches10Adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_immediate_matches.*

@AndroidEntryPoint
class ImmediateMatchesFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var match10Adapter: Matches10Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_immediate_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        //      SWIPE
        rvImmediateMatches.setOnTouchListener(object : SwipeNavigationMatches(view.context) {
            override fun onSwipeLeft() {
                findNavController().popBackStack()
                Toast.makeText(
                    context,
                    "Матчи на сегодня",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
        //      END SWIPE
        viewModel.match10LiveData.observe(viewLifecycleOwner) {
            match10Adapter?.differ?.submitList(it)
        }
    }

    private fun initAdapter() {
        match10Adapter = Matches10Adapter()
        rvImmediateMatches.apply {
            adapter = match10Adapter
        }

    }
}