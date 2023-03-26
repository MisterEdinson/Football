package com.example.football.ui.ligue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.football.R
import com.example.football.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ligs.*

@AndroidEntryPoint
class LigsFragment : Fragment() {

    private val viewModel by viewModels<LigueViewModel>()
    private var tableAdapter : TableLigueAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ligs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ligueCode = arguments?.getString("ligue")

        initAdapter()
        viewModel.getTable(ligueCode.toString())
        viewModel.ligsTableLiveData.observe(viewLifecycleOwner , Observer{
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        context?.let { it1 -> Glide.with(it1).load(it?.competition?.emblem).into(imgLigueLogo) }
                        tvAreaName.text = it?.area?.name
                        tvCompetitionType.text = it?.competition?.type
                        tvCompetitonName.text = it?.competition?.name
                        tvCurrencyDay.text = it?.season?.currentMatchday.toString()
                        it?.standings.let {
                            tableAdapter?.differ?.submitList(it?.get(0)?.table)
                        }
                    }
                }
                is Resource.Loading -> {
                    it.data.let {
                        Log.d("Log:", "Loading table")
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
        tableAdapter = TableLigueAdapter()
        rvTable.apply {
            adapter = tableAdapter
        }
    }
}