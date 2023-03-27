package com.example.football.ui.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.football.R
import com.example.football.ui.ligue.LigueViewModel
import com.example.football.ui.ligue.TableLigueAdapter
import com.example.football.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ligs.*
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.item_lig_home.view.*

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private val viewModel by viewModels<TeamViewModel>()
    private var playerAdapter : TeamPlayerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamId = arguments?.getString("idTeam")
        Log.d("-----------team read ----","${teamId}")
        teamId?.let { viewModel.getTeam(it) }
        initAdapter()
        viewModel.teamLiveData.observe(viewLifecycleOwner , Observer{
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        imgTeamLogo.loadImage(it?.crest.toString())
                        tvStadiumTeam.text = it?.venue
                        tvNameTeam.text = it?.name
                        tvCountryTeam.text = it?.area?.name
                        tvYearsTeam.text = it?.founded.toString()
                        tvCouchTeam.text = it?.coach?.name
                        tvCouchNationaleTeam.text = "(${it?.coach?.nationality})"
                        tvTeamCount.text = "Команда (${it?.squad?.size})"
                        it?.squad.let {
                            playerAdapter?.differ?.submitList(it)
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
    fun ImageView.loadImage(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadImage.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
    }
    private fun initAdapter() {
        playerAdapter = TeamPlayerAdapter()
        rvTeamPlayers.apply {
            adapter = playerAdapter
        }
    }
}