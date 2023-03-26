package com.example.football.ui.ligue

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
import com.example.football.data.host.table.TableItem
import kotlinx.android.synthetic.main.item_table_team.view.*

class TableLigueAdapter : RecyclerView.Adapter<TableLigueAdapter.TableViewHolder>() {

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<TableItem>() {
        override fun areItemsTheSame(
            oldItem: TableItem,
            newItem: TableItem
        ): Boolean {
            return oldItem.position == newItem.position
        }

        override fun areContentsTheSame(
            oldItem: TableItem,
            newItem: TableItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_team, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            tvTableNumber.text = item.position.toString()
            tvTableName.text = item.team?.name
            item.team?.crest.let {
                imgTableLable.loadImage(it.toString())
            }
            tvTableGame.text = item.playedGames.toString()
            tvTableResult.text = item.points.toString()
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