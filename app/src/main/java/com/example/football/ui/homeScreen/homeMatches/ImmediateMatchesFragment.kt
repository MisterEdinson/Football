package com.example.football.ui.homeScreen.homeMatches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.football.R
import com.example.football.ui.homeScreen.HomeViewModel
import com.example.football.ui.homeScreen.SwipeNavigationMatches
import com.example.football.ui.homeScreen.adapters.Matches10Adapter
import com.example.football.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_immediate_matches.*

@AndroidEntryPoint
class ImmediateMatchesFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private var match10Adapter : Matches10Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_immediate_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        //      SWIPE
        rvImmediateMatches.setOnTouchListener(object : SwipeNavigationMatches(view.context){
            override fun onSwipeLeft() {
                findNavController().popBackStack()
                Toast.makeText(
                    context,
                    "Матчи на сегодня",
                    Toast.LENGTH_SHORT)
                    .show()
            }
//            override fun onSwipeRight() {
//                findNavController().popBackStack()
//                Toast.makeText(
//                    context,
//                    "Матчи на сегодня",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        })

        //      END SWIPE
        viewModel.match10LiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        match10Adapter?.differ?.submitList(it?.matches)
                    }
                }
                is Resource.Loading -> {
                    it.data.let {
                        Log.d("loading data", "$it")
                    }
                }
                is Resource.Error -> {
                    it.data.let {
                        Log.e("check data", "Home fragment: error : $it")
                    }
                }
            }
        }
    }

        private fun initAdapter() {
        match10Adapter = Matches10Adapter()
        rvImmediateMatches.apply {
            adapter = match10Adapter
        }

    }
}