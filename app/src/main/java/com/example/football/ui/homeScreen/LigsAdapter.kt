package com.example.football.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
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

    private val TYPE_SKIPPED_ITEM = 0
    private val TYPE_NORMAL_ITEM = 1

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
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_SKIPPED_ITEM
        } else {
            TYPE_NORMAL_ITEM
        }
    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigsViewHolder {
        return when (viewType) {
            TYPE_SKIPPED_ITEM -> {
                LigsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lig_home, parent, false))
            }
            TYPE_NORMAL_ITEM -> {
                LigsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lig_home, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: LigsViewHolder, position: Int) {
        val item = differ.currentList[position]
        if (item.type == "CUP") {
            // не выполняйте никаких действий для пропущенного элемента
            return
        }
        holder.itemView.apply {
            item.emblem?.let {
                imgItemLig.loadImage(it)
            }
            imgItemLig.setOnClickListener {
                val bundle = bundleOf("ligue" to item.code)
                findNavController().navigate(R.id.action_homeFragment_to_ligsFragment, bundle)
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
            .placeholder(R.drawable.fifa2)
            .error(R.drawable.error)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
    }
}