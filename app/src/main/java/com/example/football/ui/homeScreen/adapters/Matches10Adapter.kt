package com.example.football.ui.homeScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.football.R
import com.example.football.data.host.matches.MatchesItem
import kotlinx.android.synthetic.main.item_match_home.view.*
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Matches10Adapter:RecyclerView.Adapter<Matches10Adapter.MatchesViewHolder>() {
    class MatchesViewHolder(view: View):RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<MatchesItem>() {
        override fun areItemsTheSame(
            oldItem: MatchesItem,
            newItem: MatchesItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MatchesItem,
            newItem: MatchesItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_home,parent,false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            item.homeTeam?.crest?.let { homeGame.loadImage(it) }
            tvHomer.text = item.homeTeam?.shortName
            tvAwey.text = item.awayTeam?.shortName
            item.awayTeam?.crest?.let { awayGame.loadImage(it) }
            if(item.status == "TIMED"){
                val inputText = DateTimeFormatter.ofPattern("dd.MM HH:mm", Locale.getDefault())
                val date = item.utcDate
                val dateTime = OffsetDateTime.parse(date)
                val time = dateTime.format(inputText)
                tvResultTime.text = time.toString()
            }else{
                tvResult.text = "Live"
                tvResultTime.text = "${item.score?.fullTime?.home}-${item.score?.fullTime?.away}"
            }
            tvHomer.setOnClickListener {
                val bundle = bundleOf("idTeam" to item.homeTeam?.id.toString())
                findNavController().navigate(R.id.action_homeFragment_to_teamFragment,bundle)
            }
            tvAwey.setOnClickListener {
                val bundle = bundleOf("idTeam" to item.awayTeam?.id.toString())
                findNavController().navigate(R.id.action_homeFragment_to_teamFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
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
}