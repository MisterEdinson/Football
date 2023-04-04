package com.example.football.ui.homeScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.football.R
import com.example.football.data.room.models.FootballMatchesDayEntity
import kotlinx.android.synthetic.main.item_match_home.view.*
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<FootballMatchesDayEntity>() {

        override fun areItemsTheSame(
            oldItem: FootballMatchesDayEntity,
            newItem: FootballMatchesDayEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FootballMatchesDayEntity,
            newItem: FootballMatchesDayEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match_home, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            item.hometeamcrest?.let { homeGame.loadImage(it) }
            tvHomer.text = item.hometeamshort
            tvAwey.text = item.awayteamshort
            item.awayteamcrest?.let { awayGame.loadImage(it) }
            if (item.status == "TIMED") {
                val inputText = DateTimeFormatter.ofPattern("HH:mm")
                val date = item.utcdate
                val dateTime = OffsetDateTime.parse(date)
                val time = dateTime.format(inputText)
                tvResultTime.text = time.toString()
            } else {
                tvResult.text = "Live"
                tvResultTime.text = "${item.scorefulltimehome}-${item.scorefulltimeaway}"
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