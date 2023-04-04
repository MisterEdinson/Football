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
import com.example.football.data.room.models.FootballMatchImmediateEntity
import kotlinx.android.synthetic.main.item_match_home.view.*
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Matches10Adapter : RecyclerView.Adapter<Matches10Adapter.MatchesViewHolder>() {
    class MatchesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<FootballMatchImmediateEntity>() {
        override fun areItemsTheSame(
            oldItem: FootballMatchImmediateEntity,
            newItem: FootballMatchImmediateEntity
        ): Boolean {
            return oldItem.idmatch == newItem.idmatch
        }

        override fun areContentsTheSame(
            oldItem: FootballMatchImmediateEntity,
            newItem: FootballMatchImmediateEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match_home, parent, false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            item.hometeamcrest?.let { homeGame.loadImage(it) }
            tvHomer.text = item.hometeamshort
            tvAwey.text = item.awayteamshort
            item.awayteamcrest?.let { awayGame.loadImage(it) }
            if (item.status == "TIMED") {
                val inputText = DateTimeFormatter.ofPattern("dd.MM HH:mm", Locale.getDefault())
                val date = item.utcdate
                val dateTime = OffsetDateTime.parse(date)
                val time = dateTime.format(inputText)
                tvResultTime.text = time.toString()
            } else {
                tvResult.text = "Live"
                tvResultTime.text = "${item.scorefulltimehome}-${item.scorefulltimeaway}"
            }
            tvHomer.setOnClickListener {
                val bundle = bundleOf("idTeam" to item.hometeamid.toString())
                findNavController().navigate(R.id.action_homeFragment_to_teamFragment, bundle)
            }
            tvAwey.setOnClickListener {
                val bundle = bundleOf("idTeam" to item.awayteamid.toString())
                findNavController().navigate(R.id.action_homeFragment_to_teamFragment, bundle)
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