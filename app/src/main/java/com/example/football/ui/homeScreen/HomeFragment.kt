package com.example.football.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.football.databinding.FragmentHomeBinding
import com.example.football.ui.homeScreen.adapters.LigsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val _binding get() = binding!!

    private lateinit var viewModel: HomeViewModel
    private var ligsAdapter: LigsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.ligsLiveData.observe(viewLifecycleOwner) { it ->
            it.let {
                ligsAdapter?.differ?.submitList(it)
            }
        }
    }

    private fun initAdapter() {
        ligsAdapter = LigsAdapter()
        imgLigs.apply {
            adapter = ligsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}