package com.example.football.ui.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.football.databinding.FragmentHomeBinding
import com.example.football.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val _binding get() = binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.competitionLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        Log.d("--------------------------------", "-------${it?.count}-----------")
                    }
                }
                is Resource.Loading -> {
                    it.data.let {
                        Log.d("--------------------------------", "-------Loading-----------")
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
}