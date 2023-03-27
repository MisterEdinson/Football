package com.example.football.ui.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.data.host.model.CompetitionsItem

class Matches10Adapter:RecyclerView.Adapter<Matches10Adapter.MatchesViewHolder>() {
    class MatchesViewHolder(view: View):RecyclerView.ViewHolder(view)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_home,parent,false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val item = differ.currentList[position]
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}