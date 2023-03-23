package com.example.football.ui.homeScreen

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
import com.example.football.data.host.model.CompetitionsItem
import kotlinx.android.synthetic.main.item_lig_home.view.*


class LigsAdapter : RecyclerView.Adapter<LigsAdapter.LigsViewHolder>() {
    class LigsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<CompetitionsItem>() {
        override fun areItemsTheSame(
            oldItem: CompetitionsItem,
            newItem: CompetitionsItem
        ): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(
            oldItem: CompetitionsItem,
            newItem: CompetitionsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lig_home, parent, false)
        return LigsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LigsViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.itemView.apply {
            item.emblem?.let { imgItemLig.loadImage(it) }
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
            .placeholder(R.drawable.fifa)
            .error(R.drawable.error)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}