package com.example.football.ui.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football.databinding.FragmentHomeBinding
import com.example.football.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val _binding get() = binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private var ligsAdapter : LigsAdapter? = null
    private var matchAdapter : MatchesAdapter? = null
    private var match10Adapter : Matches10Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.ligsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        ligsAdapter?.differ?.submitList(it?.competitions)
                    }
                }
                is Resource.Loading -> {
                    it.data.let {
                        Log.d("----------------Ligs----------------", "-------Loading-----------")
                    }
                }
                is Resource.Error -> {
                    it.data.let {
                        Log.e("check data", "Home fragment: error : $it")
                    }
                }
            }
        })
        viewModel.matchLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        matchAdapter?.differ?.submitList(it?.matches)
                    }
                }
                is Resource.Loading -> {
                    it.data.let {
                        Log.d("-----------------Match Home---------------", "-------Loading-----------")
                    }
                }
                is Resource.Error -> {
                    it.data.let {
                        Log.e("check data", "Home fragment: error : $it")
                    }
                }
            }
        })
    }

    private fun initAdapter() {
        ligsAdapter = LigsAdapter()
        imgLigs.apply {
            adapter = ligsAdapter
        }
        matchAdapter = MatchesAdapter()
        matchDay.apply {
            adapter = matchAdapter
        }
        match10Adapter = Matches10Adapter()
        rvMatch10Day.apply {
            adapter = match10Adapter
        }
    }

}